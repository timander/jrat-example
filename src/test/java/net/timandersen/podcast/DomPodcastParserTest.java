package net.timandersen.podcast;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;


public class DomPodcastParserTest extends TestCase {

    @Test
    public void testParse() {
        DomPodcastParser parser = new DomPodcastParser();
        List<Podcast> podcasts = parser.parse();
        assertEquals(212, podcasts.size());
    }


}
