package com.chekingURL.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class CheckURLTest {
	
	WebDriver driver;
	CheckingURLpage page;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.criver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		page = new CheckingURLpage(driver);
		driver.get("https://www.choucairtesting.com/empleos-testing/");
	}

	@Test
	public void f() {
		assertTrue(page.checkingPageURL(), "Estos son los links fallidos");
	}
	

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
