package com.teschelevator.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ParkDetailPage {
	
private WebDriver webDriver;
	
	public ParkDetailPage(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	public String getParkName() {
		return webDriver.findElement(By.className("detail-name")).getText();
	}
	
	public int getTodayHighTemp() {
		return Integer.parseInt(webDriver.findElement(By.id("high-temp")).getText());
	}
	
	public String getWeatherComment() {
		return webDriver.findElement(By.id("weather-comment")).getText();
	}
	
	public ParkDetailPage clickConvertToCelsius() {
		webDriver.findElement(By.linkText("Convert to Celsius")).click();
		return this;
	}
	
	
	

}
