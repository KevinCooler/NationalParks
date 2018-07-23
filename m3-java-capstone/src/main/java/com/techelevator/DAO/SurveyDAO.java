package com.techelevator.DAO;

import java.util.Map;

import com.techelevator.Object.Survey;

public interface SurveyDAO {
	
	public Map<String, Integer> getSurveyCounts();
	public void save(Survey survey);

}
