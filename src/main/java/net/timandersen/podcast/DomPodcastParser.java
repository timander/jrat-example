package net.timandersen.podcast;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class DomPodcastParser implements PodcastParser {

    @Override
    public List<Podcast> parse() {
        Document dom = buildDom();
        return parsePodcastsFrom(dom);
    }

    private List<Podcast> parsePodcastsFrom(Document dom) {
        List<Podcast> podcasts = new ArrayList<Podcast>();
        Element documentElement = dom.getDocumentElement();
        NodeList nodeList = documentElement.getElementsByTagName("item");
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                podcasts.add(getPodcast(element));
            }
        }
        return podcasts;
    }

    private Document buildDom() {
        Document dom = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("astronomycast.xml");
            dom = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(inputStream);
            if (inputStream != null) inputStream.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dom;
    }

    private Podcast getPodcast(Element element) {
        String title = getTextValue(element, "title");
        String link = getTextValue(element, "link");
        String description = getTextValue(element, "description");
        String date = getTextValue(element, "pubDate");
        String author = getTextValue(element, "author");
        return new Podcast(title, link, description, date, author);
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal;
    }

}