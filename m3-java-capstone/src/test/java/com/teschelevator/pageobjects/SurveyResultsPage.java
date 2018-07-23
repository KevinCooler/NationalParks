package com.teschelevator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SurveyResultsPage {
	
	private WebDriver webDriver;
	
	public SurveyResultsPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public int getVoteCount(String park) {
		return Integer.parseInt(webDriver.findElement(By.id(park + " votes")).getText());
	}

}
