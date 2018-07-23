package com.techelevator.DAO;

import java.util.List;

import com.techelevator.Object.Weather;

public interface WeatherDAO {
	
	List<Weather> getWeatherListByParkCode(String parkCode, boolean convertToCelsius);

}
