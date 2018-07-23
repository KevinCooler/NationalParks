package com.teschelevator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SurveyPage {
	
	private WebDriver webDriver;
	
	public SurveyPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public SurveyPage enterEmail(String email) {
		WebElement emailField = webDriver.findElement(By.id("email-input"));
		emailField.sendKeys(email);
		
		return this;
	}
	
	public SurveyPage enterActivity(String activity) {
		WebElement activityField = webDriver.findElement(By.id(activity));
		activityField.click();
		
		return this;
	}
	
	public SurveyPage choosePark(String park) {
		Select parkChoice = new Select(webDriver.findElement(By.id("form-park-name")));
		parkChoice.selectByVisibleText(park);
		
		return this;
	}
	
	public SurveyPage chooseState(String state) {
		Select stateChoice = new Select(webDriver.findElement(By.name("state")));
		stateChoice.selectByVisibleText(state);
		
		return this;
	}
	
	public SurveyResultsPage submitForm() {
		WebElement submitButton = webDriver.findElement(By.className("submit-button"));
		submitButton.click();
		return new SurveyResultsPage(webDriver);
	}
}








