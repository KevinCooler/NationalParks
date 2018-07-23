package com.techelevator.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Object.Park;

@Component
public class JDBCParkDAO implements ParkDAO {
	
	private JdbcTemplate temp;
	
	@Autowired
	public JDBCParkDAO(DataSource datasource) {
		temp = new JdbcTemplate(datasource);
	}

	@Override
	public List<Park> getParkList() {
		List<Park> parks = new ArrayList<Park>();
		
		String SqlStatement = "SELECT * FROM park order by parkname;";
		SqlRowSet results = temp.queryForRowSet(SqlStatement);
		
		while (results.next()) {
			parks.add(mapRowToParkList(results));
		}
		
		return parks;
	}

	@Override
	public Park getParkByID(String parkCode) {
		String SqlStatement = "SELECT * FROM park WHERE parkcode=?;";
		SqlRowSet results = temp.queryForRowSet(SqlStatement, parkCode.toUpperCase());
		results.next();

		return mapRowToPark(results);
	}

	private Park mapRowToParkList(SqlRowSet results) {
		Park park = new Park();
		
		park.setParkCode(results.getString("parkcode"));
		park.setParkName(results.getString("parkname"));
		park.setState(results.getString("state"));
		park.setParkDescription(results.getString("parkdescription"));
		
		return park;
	}

	private Park mapRowToPark(SqlRowSet results) {
		Park park = mapRowToParkList(results);
		
		park.setAcreage(results.getInt("acreage"));
		park.setElevationInFeet(results.getInt("elevationinfeet"));
		park.setMilesOfTrail(results.getDouble("milesoftrail"));
		park.setNumberOfCampsites(results.getInt("numberofcampsites"));
		park.setClimate(results.getString("climate"));
		park.setYearFounded(results.getInt("yearfounded"));
		park.setAnnualVisitorCount(results.getInt("annualvisitorcount"));
		park.setInspirationalQuote(results.getString("inspirationalquote"));
		park.setInspirationalQuoteSource(results.getString("inspirationalquotesource"));
		park.setEntryFee(results.getInt("entryfee"));
		park.setNumberOfAnimalSpecies(results.getInt("numberofanimalspecies"));
		
		return park;
	}
}
