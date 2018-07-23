package com.techelevator;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.techelevator.Object.Park;


public class IntegrationTestUtilities {
	
	private JdbcTemplate temp;

	public IntegrationTestUtilities(DataSource datasource) {
		temp = new JdbcTemplate(datasource);
	}
	
	public void addTestParks() {
		String sqlInsert = "INSERT INTO park (parkcode, parkname, state, acreage, elevationinfeet, milesoftrail, "
				+ "numberofcampsites, climate, yearfounded, annualvisitorcount, inspirationalquote, "
				+ "inspirationalquotesource, parkdescription, entryfee, numberofanimalspecies) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		temp.update(sqlInsert, "ABCD", "Testing National Park", "CO", 4000, 2000, 6000.0, 7, "Rainforest",
				1904, 1000000, "Testing inspires me", "Myself", "A test park.", 50, 500);
		temp.update(sqlInsert, "WXYZ", "Testing National Park 2", "MO", 6000, 4000, 2000, 8, "Temperate",
				1932, 2000000, "Testing further inspires me", "Yourself", "Another test park.", 20, 15);
		temp.update(sqlInsert, "ABYZ", "Testing National Park 3", "WY", 2000, 6000, 4000, 9, "Desert",
				1893, 3000, "Testing once again inspires me", "Everyone", "Yet another test park.", 30, 8000);
	}
	
	public void addTestWeather() {
		String sqlInsert = "INSERT INTO weather (parkcode, fivedayforecastvalue, low, high, forecast) "
				+ "VALUES (?, ?, ?, ?, ?);";
		
		temp.update(sqlInsert, "ABCD", 1, 60, 76, "thunderstorms");
		temp.update(sqlInsert, "ABCD", 2, 60, 76, "sunny");
		temp.update(sqlInsert, "ABCD", 3, 60, 76, "rain");
		temp.update(sqlInsert, "ABCD", 4, 60, 76, "sunny");
		temp.update(sqlInsert, "ABCD", 5, 60, 76, "partly cloudy");
		
		temp.update(sqlInsert, "WXYZ", 1, 10, 19, "snow");
		temp.update(sqlInsert, "WXYZ", 2, 10, 19, "cloudy");
		temp.update(sqlInsert, "WXYZ", 3, 10, 19, "sunny");
		temp.update(sqlInsert, "WXYZ", 4, 10, 19, "partly cloudy");
		temp.update(sqlInsert, "WXYZ", 5, 10, 19, "partly cloudy");
		
		temp.update(sqlInsert, "ABYZ", 1, 40, 65, "rain");
		temp.update(sqlInsert, "ABYZ", 2, 40, 65, "partly cloudy");
		temp.update(sqlInsert, "ABYZ", 3, 40, 65, "sunny");
		temp.update(sqlInsert, "ABYZ", 4, 40, 65, "sunny");
		temp.update(sqlInsert, "ABYZ", 5, 40, 65, "rain");
	}
	
	public void addTestSurveys() {
		String sqlInsert = "INSERT INTO survey_result (parkcode, emailaddress, state, activitylevel) "
				+ "VALUES (?, ?, ?, ?)";
		
		temp.update(sqlInsert, "ABCD", "testguy@gmail.com", "OH", "Active");
		temp.update(sqlInsert, "ABCD", "testguy@gmail.com", "OH", "Active");
		temp.update(sqlInsert, "WXYZ", "testguy@gmail.com", "OH", "Active");
		temp.update(sqlInsert, "ABYZ", "testguy@gmail.com", "OH", "Active");
	}
	
	public boolean testForParkCode(List<Park> parks, String code) {
		for(Park park : parks) {
			if(park.getParkCode().equals(code))
				return true;
		}
		
		return false;
	}
	
	public boolean testForParkName(Park park, String name) {
		if(park.getParkName().equals(name))
			return true;
		
		return false;
	}
}
