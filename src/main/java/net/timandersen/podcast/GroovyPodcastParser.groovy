package net.timandersen.podcast

class GroovyPodcastParser {

  def podcasts

  def void runExample() {

    def atomData = new XmlSlurper().parse(getClass().classLoader.getResourceAsStream("astronomycast.xml"))

    podcasts = []

    atomData.channel.item.each {
      def podcast = new Podcast()
      podcast.title = it.title
      podcast.link = it.link
      podcast.description = it.description
      podcast.author = it.author
      podcast.date = it.pubDate
      podcasts.add(podcast)
    }

  }

}
