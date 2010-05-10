package net.timandersen.podcast;

import com.ximpleware.AutoPilot;
import com.ximpleware.VTDGen;
import com.ximpleware.VTDNav;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class VtdPodcastParser {

    private List<Podcast> podcasts;

    public void runExample() {
        podcasts = new ArrayList<Podcast>();
        try {
            URL resource = getClass().getClassLoader().getResource("astronomycast.xml");
            BufferedInputStream xmlContent = (BufferedInputStream) resource.getContent();
            byte[] b = new byte[(xmlContent).available()];
            xmlContent.read(b);
            xmlContent.close();
            String xmlContentAsString = new String(b);

            VTDGen vg = new VTDGen();
            vg.setDoc(xmlContentAsString.getBytes("UTF-8"));
            vg.parse(true);
            VTDNav nav = vg.getNav();

            podcasts = new ArrayList<Podcast>();

            List<String> titles = extractNodeText(nav, "/rss/channel/item/title/text()");
            List<String> authors = extractNodeText(nav, "/rss/channel/item/author/text()");
            List<String> links = extractNodeText(nav, "/rss/channel/item/link/text()");
            List<String> desriptions = extractNodeText(nav, "/rss/channel/item/description/text()");
            List<String> dates = extractNodeText(nav, "/rss/channel/item//pubDate/text()");

            for (int i = 0; i < titles.size(); i++) {
                podcasts.add(new Podcast(titles.get(i), links.get(i), desriptions.get(i), dates.get(i), authors.get(i)));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


    private List<String> extractNodeText(VTDNav nav, String xpath) throws Exception {

        AutoPilot pilot = new AutoPilot(nav);
        pilot.selectXPath(xpath);
        int result = pilot.evalXPath();

        List<String> results = new ArrayList<String>();
        while (result != -1) {
            String textResult = nav.toString(result);
            results.add(textResult);
            result = pilot.evalXPath();
        }
        pilot.resetXPath();
        return results;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }
}
