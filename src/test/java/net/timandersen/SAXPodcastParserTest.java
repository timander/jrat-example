package net.timandersen;

import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;


public class SAXPodcastParserTest extends TestCase {

    @Test
    public void testParse() {

        SAXPodcastParser parser = new SAXPodcastParser();
        parser.runExample();
        assertEquals(212, parser.getPodcasts().size());
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring/application-context.xml");

        PodcastRepository podcastRepository = (PodcastRepository) context.getBean("podcastRepository");
        podcastRepository.saveOrUpdateAll(parser.getPodcasts());

        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");
        int countOfPodcasts = jdbcTemplate.queryForInt("select count(*) from Podcasts");
        assertEquals(parser.getPodcasts().size(), countOfPodcasts);

    }


}
