package net.timandersen.podcast;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;


public class SAXPodcastParserTest extends TestCase {

    @Test
    public void testParse() {
        SAXPodcastParser parser = new SAXPodcastParser();
        List<Podcast> podcasts = parser.parse();
        assertEquals(212, podcasts.size());
    }


}
