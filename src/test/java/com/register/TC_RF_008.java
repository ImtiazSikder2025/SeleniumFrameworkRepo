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

import com.utils.CommonUtilities;

public class TC_RF_008 {

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
			
		driver.findElement(By.xpath("//span[@class='caret']")).click();
		driver.findElement(By.linkText("Register")).click();
	}	
	
	
	//Verify Registering an account by entering different passwords in
	//Password and Password Confirm fields
	
	@Test
	public void verifyRegisteringAccountByEnteringDifferentPasswords() {
		
		driver.findElement(By.name("firstname")).sendKeys("Bret");
		driver.findElement(By.name("lastname")).sendKeys("Hart");
		driver.findElement(By.name("email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.name("telephone")).sendKeys("345544544");
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).click();
		driver.findElement(By.name("agree")).click();
		
		//Entering different passwords into Password and Password Confirm fields
		driver.findElement(By.name("password")).sendKeys("675677");
		driver.findElement(By.name("confirm")).sendKeys("sdfdsf");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		
		
		//Verify
		boolean flag=driver.findElement(By.xpath("//div[text()='Password confirmation does not match password!']")).isDisplayed();
		Assert.assertTrue(flag);
	}
	
	@AfterMethod
	public void teardown() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
}
