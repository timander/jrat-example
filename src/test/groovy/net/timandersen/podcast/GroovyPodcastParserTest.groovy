package net.timandersen.podcast

import org.junit.Test

class GroovyPodcastParserTest {

  @Test
  def void testRunExample() {
    def parser = new GroovyPodcastParser()
    def podcasts = parser.parse()
    //parser.getPodcasts().each {println it}
    assert 212 == podcasts.size()
  }

}
