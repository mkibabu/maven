package org.mvnex.custom.weather;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class YahooRetriever {

    private static Logger log = Logger.getLogger(YahooRetriever.class);

    public InputStream retrieve(String zipcode) throws Exception {
        log.info( "Retrieving Weather Data" );
        String query = "select * from weather.forecast where woeid in "+ 
        "(select woeid from geo.places(1) where text=\"" + zipcode + "\")";
        String url = "https://query.yahooapis.com/v1/public/yql?q="
          + URLEncoder.encode(query, "UTF-8");
        URLConnection conn = new URL(url).openConnection();
        return conn.getInputStream();
    }
}
