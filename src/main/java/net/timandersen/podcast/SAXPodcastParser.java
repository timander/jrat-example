package net.timandersen.podcast;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SAXPodcastParser implements PodcastParser {

    @Override
    public List<Podcast> parse() {
        List<Podcast> podcasts = new ArrayList<Podcast>();
        SaxHandler saxHandler = new SaxHandler(podcasts);
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("astronomycast.xml");
            SAXParserFactory.newInstance().newSAXParser().parse(inputStream, saxHandler);
            if (inputStream != null) inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.unmodifiableList(podcasts);
    }

    private class SaxHandler extends DefaultHandler {
        private String value;
        private Podcast podcast;
        private List<Podcast> podcasts;

        private SaxHandler(List<Podcast> podcasts) {
            this.podcasts = podcasts;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //reset
            value = "";
            if ("item".equalsIgnoreCase(qName)) {
                podcast = new Podcast();
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            value = new String(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (podcast == null) podcast = new Podcast();
            if ("item".equalsIgnoreCase(qName)) podcasts.add(podcast);
            if ("title".equalsIgnoreCase(qName)) podcast.setTitle(value);
            if ("link".equalsIgnoreCase(qName)) podcast.setLink(value);
            if ("description".equalsIgnoreCase(qName)) podcast.setDescription(value);
            if ("pubDate".equalsIgnoreCase(qName)) podcast.setDate(value);
            if ("author".equalsIgnoreCase(qName)) podcast.setAuthor(value);
        }

        public List<Podcast> getPodcasts() {
            return podcasts;
        }

    }

}