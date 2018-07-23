package com.techelevator;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.DAO.JDBCSurveyDAO;
import com.techelevator.Object.Survey;

public class JDBCSurveyIntegrationTest extends DAOIntegrationTest{

	private JDBCSurveyDAO surveyDao;
	private IntegrationTestUtilities util;
	
	@Before
	public void setup() {
		surveyDao = new JDBCSurveyDAO(getDataSource());
		util = new IntegrationTestUtilities(getDataSource());
		util.addTestParks();
		util.addTestSurveys();
	}
	
	@Test
	public void testSurveyCount() {
		Map<String, Integer> test = surveyDao.getSurveyCounts();
		
		Assert.assertTrue(test.get("ABCD") == 2);
		Assert.assertTrue(test.get("WXYZ") == 1);
		Assert.assertTrue(test.get("ABYZ") == 1);
		Assert.assertFalse(test.get("ABCD") == 5);
	}
	
	@Test
	public void testSave() {
		Survey survey = new Survey();
		survey.setParkCode("ABCD");
		survey.setEmailAddress("testguy@gmail.com");
		survey.setState("OH");
		survey.setActivityLevel("Active");
		
		Map<String, Integer> test = surveyDao.getSurveyCounts();
		Assert.assertTrue(test.get("ABCD") == 2);
		
		surveyDao.save(survey);
		
		test = surveyDao.getSurveyCounts();
		Assert.assertTrue(test.get("ABCD") == 3);
	}
}