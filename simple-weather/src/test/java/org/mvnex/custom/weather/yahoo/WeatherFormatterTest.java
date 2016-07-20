package org.mvnex.custom.weather.yahoo;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import org.mvnex.custom.weather.Weather;
import org.mvnex.custom.weather.WeatherFormatter;
import org.mvnex.custom.weather.YahooParser;

import junit.framework.TestCase;

public class WeatherFormatterTest extends TestCase {

	public WeatherFormatterTest(String name) {
		super(name);
	}

	@SuppressWarnings("deprecation")
	public void testFormat() throws Exception {
		InputStream nyData = getClass().getClassLoader()
				.getResourceAsStream("ny-weather.xml");
		Weather weather = new YahooParser().parse(nyData);
		String formattedResult = new WeatherFormatter().format(weather);
		InputStream expected = getClass().getClassLoader()
				.getResourceAsStream("format-expected.dat");
		assertEquals(IOUtils.toString(expected).trim(), formattedResult.trim());
	}
}
