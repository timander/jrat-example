package net.timandersen.podcast

class GroovyPodcastParser implements PodcastParser{

  def List<Podcast> parse() {

    def rssData = new XmlSlurper().parse(getClass().classLoader.getResourceAsStream("astronomycast.xml"))

    def podcasts = []

    rssData.channel.item.each {
      def podcast = new Podcast()
      podcast.title = it.title
      podcast.link = it.link
      podcast.description = it.description
      podcast.author = it.author
      podcast.date = it.pubDate
      podcasts.add(podcast)
    }

    podcasts

  }

}
