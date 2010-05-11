package net.timandersen.podcast;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-context.xml");

    public static void main(String[] args) {
        Map<String, PodcastParser> parsingStrategy = new HashMap<String, PodcastParser>();
        parsingStrategy.put("sax", new SAXPodcastParser());
        parsingStrategy.put("dom", new DomPodcastParser());
        parsingStrategy.put("vtd", new VtdPodcastParser());
        parsingStrategy.put("groovy", new GroovyPodcastParser());

        PodcastParser parser = parsingStrategy.get(args[0]);

        if (parser == null) {
            throw new RuntimeException("valid parsers are [dom, sax, vtd, groovy]");
        }

        List<Podcast> podcasts = parser.parse();
        save(podcasts);
    }

    private static void save(Collection<Podcast> podcasts) {
        PodcastRepository podcastRepository = (PodcastRepository) getContext().getBean("podcastRepository");
        podcastRepository.saveOrUpdateAll(podcasts);
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
