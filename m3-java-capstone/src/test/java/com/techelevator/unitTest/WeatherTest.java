package com.techelevator.unitTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.Object.Weather;

public class WeatherTest {
	
	private Weather weather;
	
	@Before
	public void setup() {
		this.weather = new Weather();
	}
	
	@Test
	public void return_accurate_comment_when_temp_not_issue() {
		weather.setHigh(75);
		weather.setLow(65);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_hot() {
		weather.setHigh(76);
		weather.setLow(70);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes. Bring an extra gallon of water.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Bring an extra gallon of water.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Bring an extra gallon of water.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block. Bring an extra gallon of water.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_cold() {
		weather.setHigh(25);
		weather.setLow(15);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block. Exposure to frigid temperatures is dangerous.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span_and_cold() {
		weather.setHigh(65);
		weather.setLow(19);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span() {
		weather.setHigh(75);
		weather.setLow(20);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes. Wear breathable layers.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Wear breathable layers.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Wear breathable layers.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block. Wear breathable layers.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span_and_hot() {
		weather.setHigh(76);
		weather.setLow(20);
		weather.setForecast("snow");
		Assert.assertEquals("Pack snow shoes. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather.setForecast("rain");
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather.setForecast("thunderstorms");
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather.setForecast("sunny");
		Assert.assertEquals("Pack sun block. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
	}
}
