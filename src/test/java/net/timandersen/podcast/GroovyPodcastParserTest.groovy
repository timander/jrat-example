package net.timandersen.podcast

import org.junit.Test

class GroovyPodcastParserTest {

  @Test
  def void testRunExample() {
    def parser = new GroovyPodcastParser()
    parser.runExample()
    //parser.getPodcasts().each {println it}
    assert 212 == parser.channel.size()
  }

}
