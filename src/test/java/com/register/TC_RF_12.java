package com.register;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_12 {


	WebDriver driver;
	String browserName="firefox";
	
	@BeforeMethod
	public void setup() throws Exception {

		//Browser setup		
		if(browserName.equals("chrome")) {
			driver=new ChromeDriver();
		}else if(browserName.equals("firefox")) {
			driver=new FirefoxDriver();
		}else if(browserName.equals("edge")) {
			driver=new EdgeDriver();
		}		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));  
		driver.manage().window().maximize();
		driver.get("https://tutorialsninja.com/demo/");		

		//To fix the size of Browser	
		Thread.sleep(5000);	
		Robot robot = new Robot();	
		System.out.println("About to zoom out");		
		for (int i = 0; i < 2; i++) {
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_SUBTRACT);
			robot.keyRelease(KeyEvent.VK_CONTROL);			
			Thread.sleep(7000);	
		}				
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Register")).click();
	}	
	
	
	
	
	//Verify Registering an account by providing "Invalid Telephone" number
	@Test
	public void verifyRegisteringByProvidingInvaliTelephoneNumber() throws Exception {
		
		driver.findElement(By.name("firstname")).sendKeys("Bret");
		driver.findElement(By.name("lastname")).sendKeys("Hart");
		driver.findElement(By.name("email")).sendKeys("isikder");
		driver.findElement(By.name("telephone")).sendKeys("TTTT");
		driver.findElement(By.name("password")).sendKeys("pppppp");
		driver.findElement(By.name("confirm")).sendKeys("pppppp");
		driver.findElement(By.xpath("//input[@type='radio' and @value='0']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Validation ----- Validation was taken from email. Because, test was failing and didn't have expected or actual text
	String expectedMessage= "Please enter valid telephone number !!";
	String actualMessage=driver.findElement(By.id("input-email")).getText();
	Assert.assertEquals(actualMessage, expectedMessage);
	}
		
		
	
}
