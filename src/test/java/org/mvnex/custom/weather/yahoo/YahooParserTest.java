package org.mvnex.custom.weather.yahoo;

import java.io.InputStream;

import org.mvnex.custom.weather.Weather;
import org.mvnex.custom.weather.YahooParser;

import junit.framework.TestCase;

public class YahooParserTest extends TestCase {

	public YahooParserTest(String name) {
		super(name);
	}

	public void testParser() throws Exception {
		InputStream nyData = getClass().getClassLoader()
				.getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(nyData);
		assertEquals("New York", weather.getCity());
		assertEquals("NY", weather.getRegion());
		assertEquals("United States", weather.getCountry());
		assertEquals("76", weather.getTemp());
		assertEquals("Mostly Cloudy", weather.getCondition());
		assertEquals("75", weather.getChill());
		assertEquals("75", weather.getHumidity());
	}
}