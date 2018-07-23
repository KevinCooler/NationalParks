package com.techelevator.DAO;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.Object.Survey;

@Component
public class JDBCSurveyDAO implements SurveyDAO{
	
private JdbcTemplate temp;
	
	@Autowired
	public JDBCSurveyDAO(DataSource datasource) {
		temp = new JdbcTemplate(datasource);
	}

	@Override
	public Map<String, Integer> getSurveyCounts() {
		Map<String, Integer> surveyCounts = new LinkedHashMap<String, Integer>();
		String sqlSelectSurveyCounts = "select count(*), parkcode from survey_result group by parkcode order by count desc, parkcode";
		SqlRowSet result = temp.queryForRowSet(sqlSelectSurveyCounts);
		while(result.next()) {
			surveyCounts.put(result.getString("parkcode"), result.getInt("count"));
		}
		return surveyCounts;
	}

	@Override
	public void save(Survey survey) {
		String sqlInsertSurvey = "insert into survey_result (parkcode, emailaddress, state, activitylevel) values (?, ?, ?, ?);";
		temp.update(sqlInsertSurvey, survey.getParkCode().toUpperCase(), survey.getEmailAddress(), survey.getState(), survey.getActivityLevel());
	}
}
