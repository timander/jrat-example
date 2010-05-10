package net.timandersen.podcast;

import junit.framework.TestCase;
import org.junit.Test;


public class VtdPodcastParserTest extends TestCase {

    @Test
    public void testVtdParser() {
        VtdPodcastParser parser = new VtdPodcastParser();
        parser.runExample();
        assertEquals(212, parser.getPodcasts().size());
    }
}
