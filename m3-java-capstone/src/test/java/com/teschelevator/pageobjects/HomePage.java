package com.teschelevator.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
	
	private WebDriver webDriver;

	public HomePage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyPage clickSurveyLink() {
		WebElement surveyLink = webDriver.findElement(By.linkText("Survey"));
		surveyLink.click();
		return new SurveyPage(webDriver);
	}
	
	public ParkDetailPage clickParkName(String parkName) {
		WebElement firstPark = webDriver.findElement(By.linkText(parkName));
		firstPark.click();
		return new ParkDetailPage(webDriver);
	}
	
	public ParkDetailPage clickRandomParkName() {
		List<WebElement> parks = webDriver.findElements(By.partialLinkText("Park"));
		int ranChoice = (int)(Math.random() * parks.size());
		parks.get(ranChoice).click();
		return new ParkDetailPage(webDriver);
	}

}
