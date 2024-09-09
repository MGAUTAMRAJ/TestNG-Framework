package com.qc.testng.tests;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class RegisterTest  extends BaseIntegration{
	
	
	@BeforeSuite
    public void setup() throws IOException {
    	doSetup();
    }
	 
	public void ClickOnRegPage(){
		regPageLink = driver.findElement(By.linkText("Register a new membership"));
		regPageLink.click();
	}
	@BeforeMethod
	public void locateElement() {
		if(driver.getTitle().equals("Queue Codes | Log in")) {
			ClickOnRegPage();
		}
		rName = driver.findElement(By.id("name"));
		rName.clear();
		rMobile = driver.findElement(By.id("mobile"));
		rMobile.clear();
		rEmail = driver.findElement(By.id("email"));
		rEmail.clear();
		rPass = driver.findElement(By.id("password"));
		rPass.clear();
		rSignin = driver.findElement(By.tagName("button"));
		}
		
	@Test(dataProvider = "registerData")
	public void doRegister(String testName, String uName,String uMobile,String uEmail,String uPass) {
		tName = testName;
		rName.sendKeys(uName);
		rMobile.sendKeys(uMobile);
		rEmail.sendKeys(uEmail);
		rPass.sendKeys(uPass);
		rSignin.click();
	}
	
	@AfterMethod
	public void doAssert() throws InterruptedException {
		String actResult,expResult;
		
		if(tName.equals("All are valid ")) {
			 actResult = handleAlert();
			 expResult = "User registered successfully.";
			
		}else {
		 actResult = driver.getTitle();
		 expResult = "Queue Codes | Registration Page";
		}
		 Assert.assertEquals(actResult, expResult);
		Thread.sleep(1000);
	}
	
	public String handleAlert() {
		Alert alt = driver.switchTo().alert();
		String altText = alt.getText();
		alt.accept();
		return altText;
		
	}
	@AfterClass
	public void tearDown() {
		driver.close();
	}
}

