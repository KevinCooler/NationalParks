package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Object.Weather;

@Component
public class JDBCWeatherDAO implements WeatherDAO{
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCWeatherDAO(DataSource datasource) {
		temp = new JdbcTemplate(datasource);
	}

	@Override
	public List<Weather> getWeatherListByParkCode(String parkCode, boolean convertToCelsius) {
		List<Weather> weathers = new ArrayList<Weather>();
		String sqlSelectWeatherList = "select * from weather where parkcode = ? order by fivedayforecastvalue";
		SqlRowSet result = temp.queryForRowSet(sqlSelectWeatherList, parkCode.toUpperCase());
		while(result.next()) {
			Weather weather = mapRowToWeather(result);
			if(convertToCelsius) {
				weather.setHigh(doCelsiusConversion(weather.getHigh()));
				weather.setLow(doCelsiusConversion(weather.getLow()));
			}
			weathers.add(weather);
		}
		return weathers;
	}

	private int doCelsiusConversion(int fahrenheit) {
		int celsius = (int) ((fahrenheit - 32) * 0.5556);
		return celsius;
	}

	private Weather mapRowToWeather(SqlRowSet result) {
		Weather weather = new Weather();
		weather.setFiveDayForecastValue(result.getInt("fivedayforecastvalue"));
		weather.setForecast(result.getString("forecast"));
		if (weather.getForecast().equals("partly cloudy"))
			weather.setForecast("partlyCloudy");
		weather.setHigh(result.getInt("high"));
		weather.setLow(result.getInt("low"));
		weather.setParkCode(result.getString("parkcode"));
		weather.setComment(createComment(weather.getHigh(), weather.getLow(), weather.getForecast()));
		return weather;
	}

	private String createComment(int high, int low, String forecast) {
		String comment = "";
		if(forecast.equals("snow")) comment += "Pack snow shoes.";
		if(forecast.equals("rain")) comment += "Pack rain gear and wear waterproof shoes.";
		if(forecast.equals("thunderstorms")) comment += "Seek shelter and avoid hiking on exposed ridges.";
		if(forecast.equals("sunny")) comment += "Pack sun block.";
		if(high > 75) comment += " Bring an extra gallon of water.";
		if(high - low > 20) comment += " Wear breathable layers.";
		if(low < 20) comment += " Exposure to frigid temperatures is dangerous.";
		return comment;
	}

}
