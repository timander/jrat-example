package net.timandersen.podcast;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Collection;
import java.util.List;


public class Main {

    private static ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-context.xml");

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new RuntimeException("valid parsers are sax, groovy");
        }

        List<Podcast> podcasts = null;

        if ("sax".equals(args[0])) {
            SAXPodcastParser parser = new SAXPodcastParser();
            podcasts = parser.parse();
        }
        if ("vtd".equals(args[0])) {
            VtdPodcastParser parser = new VtdPodcastParser();
            podcasts = parser.parse();
        }
        else if ("groovy".equals(args[0])) {
            GroovyPodcastParser parser = new GroovyPodcastParser();
            podcasts = parser.parse();
        }
        else {
            throw new RuntimeException("valid parsers are dom, sax, groovy");
        }

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
