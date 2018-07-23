package com.techelevator;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.techelevator.DAO.JDBCParkDAO;
import com.techelevator.Object.Park;

public class JDBCParkDAOIntegrationTest extends DAOIntegrationTest{
	
	private JDBCParkDAO parkDao;
	private IntegrationTestUtilities util;
	
	@Before
	public void setup() {
		parkDao = new JDBCParkDAO(getDataSource());
		util = new IntegrationTestUtilities(getDataSource());
		util.addTestParks();
	}

	@Test
	public void initialListTest() {
		List<Park> parks = parkDao.getParkList();
		
		Assert.assertTrue(util.testForParkCode(parks, "abcd"));
		Assert.assertTrue(util.testForParkCode(parks, "wxyz"));
		Assert.assertTrue(util.testForParkCode(parks, "abyz"));
		Assert.assertFalse(util.testForParkCode(parks, "1234567"));
	}
	
	@Test
	public void initialIdTest() {
		Assert.assertTrue(util.testForParkName(parkDao.getParkByID("ABCD"), "Testing National Park"));
		Assert.assertTrue(util.testForParkName(parkDao.getParkByID("WXYZ"), "Testing National Park 2"));
		Assert.assertTrue(util.testForParkName(parkDao.getParkByID("ABYZ"), "Testing National Park 3"));
		Assert.assertFalse(util.testForParkName(parkDao.getParkByID("ABCD"), "Destined To Fail"));
	}
}
