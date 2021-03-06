package programmingclub.daiict.classes.tech_news_classes;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import programmingclub.daiict.R;

/**
 * Created by omkar13 on 12/24/2015.
 */


public class ScienceRssService extends IntentService {

    private static final String RSS_LINK = "http://feeds.arstechnica.com/arstechnica/science.rss";
    public static final String ITEMS = "items";
    public static final String RECEIVER = "receiver";

    public ScienceRssService() {
      super("ScienceRssService");
  }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("ScienceRssService", ":onHandleIntent method: Service started");
        List<RssItem> rssItems = null;
        try {
            RssParser parser = new RssParser();

            if (getInputStream(RSS_LINK)!=null)
            rssItems = parser.parse(getInputStream(RSS_LINK));

        } catch (XmlPullParserException e) {
            Log.w(e.getMessage(), e);
        } catch (IOException e) {
            Log.w(e.getMessage(), e);
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(ITEMS, (Serializable) rssItems);
        ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
        receiver.send(0, bundle);
    }

    //if we get null from this method then we pass in input stream from the database
    public InputStream getInputStream(String link) {
        try {
            URL url = new URL(link);
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            Log.w("ScienceRssService", "getInputStream Exception while retrieving the input stream", e);
            return null;
        }
    }
}
