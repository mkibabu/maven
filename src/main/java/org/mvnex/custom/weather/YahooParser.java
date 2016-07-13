package org.mvnex.custom.weather;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.io.SAXReader;
import org.dom4j.Node;

public class YahooParser {

  private static Logger log = Logger.getLogger(YahooParser.class);

  public Weather parse(InputStream inputStream) throws Exception {
    Weather weather = new Weather();

    log.info( "Creating XML Reader" );
    SAXReader xmlReader = createXmlReader();
    Document doc = xmlReader.read( inputStream );

    log.info( "Parsing XML Response" );
    weather.setCity(
      doc.valueOf("/query/results/channel/y:location/@city") );
    weather.setRegion(
      doc.valueOf("/query/results/channel/y:location/@region") );
    weather.setCountry(
      doc.valueOf("/query/results/channel/y:location/@country") );
    weather.setCondition(
      doc.valueOf("/query/results/channel/item/y:condition/@text") );
    weather.setTemp(
      doc.valueOf("/query/results/channel/item/y:condition/@temp") );
    weather.setChill(
      doc.valueOf("/query/results/channel/y:wind/@chill") );
    weather.setHumidity(
      doc.valueOf("/query/results/channel/y:atmosphere/@humidity") );

    return weather;
  }

  private SAXReader createXmlReader() {
    Map<String,String> uris = new HashMap<String,String>();
    uris.put( "y", "http://xml.weather.yahoo.com/ns/rss/1.0" );

    DocumentFactory factory = new DocumentFactory();
        factory.setXPathNamespaceURIs( uris );

    SAXReader xmlReader = new SAXReader();
      xmlReader.setDocumentFactory( factory );
    return xmlReader;
  }
}
