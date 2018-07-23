package com.techelevator;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.teschelevator.pageobjects.HomePage;
import com.teschelevator.pageobjects.ParkDetailPage;
import com.teschelevator.pageobjects.SurveyPage;
import com.teschelevator.pageobjects.SurveyResultsPage;

public class SeleniumTest{
	
	private static WebDriver webDriver;
	private HomePage homePage;

	@BeforeClass
	public static void openWebBrowserForTesting() {
		String homeDir = System.getProperty("user.home");
		System.setProperty("webdriver.chrome.driver", homeDir+"/dev-tools/chromedriver/chromedriver");
		webDriver = new ChromeDriver();
	}
	
	@Before
	public void openHomePage() {
		webDriver.get("http://localhost:8080/m3-java-capstone/");
		homePage = new HomePage(webDriver);
	}
	
	@AfterClass
	public static void closeWebBrowser() {
		webDriver.close();
	}
	
	@Test
	public void testHomepageParkList() {
		List<WebElement> test = webDriver.findElements(By.tagName("h2"));
	
		Assert.assertTrue(test.size() == 10);
	}

	@Test
	public void clickParkDetail() {
		ParkDetailPage detailPage = homePage.clickParkName("Cuyahoga Valley National Park");
		Assert.assertEquals("Cuyahoga Valley National Park", detailPage.getParkName());
	}
	
	@Test
	public void testHighTemp() {
		ParkDetailPage detailPage = homePage.clickParkName("Glacier National Park");
		Assert.assertTrue(detailPage.getTodayHighTemp() == 40);
	}
	
	@Test
	public void testWeatherComment() {
		ParkDetailPage detailPage = homePage.clickParkName("Everglades National Park");
		Assert.assertEquals(detailPage.getWeatherComment(), "Bring an extra gallon of water.");
	}
	
	
	@Test
	public void testCelsiusConversion() {
		ParkDetailPage detailPage = homePage.clickRandomParkName();
		int fahrenheit = detailPage.getTodayHighTemp();
		int celsius = (int)((fahrenheit - 32) * 0.5556);
		int conversion = detailPage.clickConvertToCelsius().getTodayHighTemp();
		Assert.assertTrue(celsius == conversion);
	}
	
	@Test
	public void form_can_be_completed_and_submitted_and_counted() {
		SurveyResultsPage firstResultPage = homePage.clickSurveyLink()
													.choosePark("Glacier National Park")
													.enterEmail("kevin@gmail.com")
													.chooseState("OH")
													.enterActivity("Inactive")
													.submitForm();
		int firstCount = firstResultPage.getVoteCount("Glacier National Park");
		
		SurveyResultsPage secondResultPage = homePage.clickSurveyLink()
														.choosePark("Glacier National Park")
														.enterEmail("kevin@gmail.com")
														.chooseState("OH")
														.enterActivity("Inactive")
														.submitForm();
		int secondCount = secondResultPage.getVoteCount("Glacier National Park");
		Assert.assertTrue(firstCount + 1 == secondCount);
	}
}
