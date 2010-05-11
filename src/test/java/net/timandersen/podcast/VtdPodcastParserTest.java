package net.timandersen.podcast;

import junit.framework.TestCase;
import org.junit.Test;
import java.util.List;


public class VtdPodcastParserTest extends TestCase {

    @Test
    public void testVtdParser() {
        VtdPodcastParser parser = new VtdPodcastParser();
        List<Podcast> podcasts = parser.parse();
        assertEquals(212, podcasts.size());
    }

}
