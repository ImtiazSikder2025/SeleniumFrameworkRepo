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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_RF_007 {

	WebDriver driver;
	
	@BeforeMethod
	public void setup() throws Exception {

		//Browser setup
		String browserName="firefox";
		
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
	
	}	
	
	
	
	//Verify different ways of navigating to Register Account page
	@Test(priority=1)
	public void verifyNavigatingToRegisterAccountPageOStepOne() {
		//Step 1
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Register")).click();
		
		//Verify
		String expectedHeading="Register Account";
		String actualHeading=driver.findElement(By.xpath("//h1[text()='Register Account']")).getText();
		Assert.assertEquals(actualHeading, expectedHeading);		
	}
	
	@Test(priority=2)
	public void verifyNavigatingToRegisterAccountPageOStepTwo() {
		//Step 2
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Login")).click();		
		driver.findElement(By.linkText("Continue")).click();
		
		//Verify
		String expectedHeadingTwo="Register Account";
		String actualHeadingTwo=driver.findElement(By.xpath("//h1[text()='Register Account']")).getText();
		Assert.assertEquals(actualHeadingTwo, expectedHeadingTwo);			
	}
	
	@Test(priority=3)
	public void verifyNavigatingToRegisterAccountPageOStepThree() {
		//Step 2
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Login")).click();		
		driver.findElement(By.linkText("Register")).click();
		
		//Verify
		String expectedHeadingThree="Register Account";
		String actualHeadingThree=driver.findElement(By.xpath("//h1[text()='Register Account']")).getText();
		Assert.assertEquals(actualHeadingThree, expectedHeadingThree);			
	}
	
	
	
	
	
	@AfterMethod
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
}
