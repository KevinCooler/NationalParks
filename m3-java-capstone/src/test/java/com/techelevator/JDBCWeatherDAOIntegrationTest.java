package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.DAO.JDBCWeatherDAO;
import com.techelevator.Object.Weather;

public class JDBCWeatherDAOIntegrationTest extends DAOIntegrationTest{
	
	private JDBCWeatherDAO weatherDAO;
	private JdbcTemplate temp;
	String testParkCode;
	Boolean convertToCelsius;
	
	@Before
	public void setup() {
		this.weatherDAO = new JDBCWeatherDAO(getDataSource());
		this.temp = new JdbcTemplate (getDataSource());
		
	}
	
	@Test
	public void return_size_plus_1_when_weather_added() {
		testParkCode = "GTNP";
		convertToCelsius = false;
		int initialSize = weatherDAO.getWeatherListByParkCode(testParkCode, convertToCelsius).size();
		String sqlInsertTestWeather = "insert into weather (parkcode, fivedayforecastvalue, low, high, forecast) values ('GTNP', 6, 50, 60, 'test')";
		temp.update(sqlInsertTestWeather);
		int finalSize = weatherDAO.getWeatherListByParkCode(testParkCode, convertToCelsius).size();
		
		Assert.assertEquals(initialSize + 1,  finalSize);
	}
	
	@Test
	public void return_correct_conversion_to_celsius() {
		testParkCode = "GTNP";
		convertToCelsius = true;
		String sqlInsertTestWeather = "insert into weather (parkcode, fivedayforecastvalue, low, high, forecast) values ('GTNP', 6, 50, 60, 'test')";
		temp.update(sqlInsertTestWeather);
		List<Weather> weathers = weatherDAO.getWeatherListByParkCode(testParkCode, convertToCelsius);
		Weather weather = null;
		for (Weather each: weathers) {
			if(each.getForecast().contentEquals("test")) weather = each;
		}
		Assert.assertEquals(15,  weather.getHigh());
		Assert.assertEquals(10,  weather.getLow());
	}
	
	@Test
	public void return_correct_temp_when_not_converting() {
		testParkCode = "GTNP";
		convertToCelsius = false;
		String sqlInsertTestWeather = "insert into weather (parkcode, fivedayforecastvalue, low, high, forecast) values ('GTNP', 6, 50, 60, 'test')";
		temp.update(sqlInsertTestWeather);
		List<Weather> weathers = weatherDAO.getWeatherListByParkCode(testParkCode, convertToCelsius);
		Weather weather = null;
		for (Weather each: weathers) {
			if(each.getForecast().contentEquals("test")) weather = each;
		}
		Assert.assertEquals(60,  weather.getHigh());
		Assert.assertEquals(50,  weather.getLow());
	}
	
	@Test
	public void return_accurate_comment_when_temp_not_issue() {
		Weather weather = getTestWeatherInserted(75, 70, "snow", 6);
		Assert.assertEquals("Pack snow shoes.", weather.getComment());
		weather = getTestWeatherInserted(75, 70, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes.", weather.getComment());
		weather = getTestWeatherInserted(75, 70, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges.", weather.getComment());
		weather = getTestWeatherInserted(75, 70, "sunny", 9);
		Assert.assertEquals("Pack sun block.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_hot() {
		Weather weather = getTestWeatherInserted(76, 70, "snow", 6);
		Assert.assertEquals("Pack snow shoes. Bring an extra gallon of water.", weather.getComment());
		weather = getTestWeatherInserted(76, 70, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Bring an extra gallon of water.", weather.getComment());
		weather = getTestWeatherInserted(76, 70, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Bring an extra gallon of water.", weather.getComment());
		weather = getTestWeatherInserted(76, 70, "sunny", 9);
		Assert.assertEquals("Pack sun block. Bring an extra gallon of water.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_cold() {
		Weather weather = getTestWeatherInserted(25, 15, "snow", 6);
		Assert.assertEquals("Pack snow shoes. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(25, 15, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(25, 15, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(25, 15, "sunny", 9);
		Assert.assertEquals("Pack sun block. Exposure to frigid temperatures is dangerous.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span_and_cold() {
		Weather weather = getTestWeatherInserted(65, 19, "snow", 6);
		Assert.assertEquals("Pack snow shoes. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(65, 19, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(65, 19, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
		weather = getTestWeatherInserted(65, 19, "sunny", 9);
		Assert.assertEquals("Pack sun block. Wear breathable layers. Exposure to frigid temperatures is dangerous.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span() {
		Weather weather = getTestWeatherInserted(75, 20, "snow", 6);
		Assert.assertEquals("Pack snow shoes. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(75, 20, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(75, 20, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(75, 20, "sunny", 9);
		Assert.assertEquals("Pack sun block. Wear breathable layers.", weather.getComment());
	}
	
	@Test
	public void return_accurate_comment_when_temp_large_span_and_hot() {
		Weather weather = getTestWeatherInserted(76, 20, "snow", 6);
		Assert.assertEquals("Pack snow shoes. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(76, 20, "rain", 7);
		Assert.assertEquals("Pack rain gear and wear waterproof shoes. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(76, 20, "thunderstorms", 8);
		Assert.assertEquals("Seek shelter and avoid hiking on exposed ridges. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
		weather = getTestWeatherInserted(76, 20, "sunny", 9);
		Assert.assertEquals("Pack sun block. Bring an extra gallon of water. Wear breathable layers.", weather.getComment());
	}
	
	private Weather getTestWeatherInserted(int high, int low, String forecast, int day) {
		testParkCode = "GTNP";
		convertToCelsius = false;
		String sqlInsertTestWeather = "insert into weather (parkcode, fivedayforecastvalue, low, high, forecast) values ('GTNP', ?, ?, ?, ?)";
		temp.update(sqlInsertTestWeather, day, low, high, forecast);
		List<Weather> weathers = weatherDAO.getWeatherListByParkCode(testParkCode, convertToCelsius);
		Weather weather = null;
		for (Weather each: weathers) {
			if(each.getFiveDayForecastValue() == day) weather = each;
		}
		return weather;
	}

}
