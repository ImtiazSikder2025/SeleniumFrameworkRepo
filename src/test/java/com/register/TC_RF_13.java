package com.register;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.utils.CommonUtilities;

public class TC_RF_13 {
	
	WebDriver driver;
	String browserName="chrome";
	
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
	
	
	@Test
	public void verifyRegisteringByUsingKeyboardKeys() {
		
		//Sequence:
		//pause -- sendKeys.Tab -- pause -- sendKeys(value)
		System.out.println("Test is starting ....");		
		Actions actions =new Actions(driver);
		
		actions.sendKeys(Keys.TAB).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(2))
		.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN)
		.pause(Duration.ofSeconds(2)).sendKeys(Keys.ENTER).build().perform();

		for(int i=1;i<=23;i++) {
			actions.sendKeys(Keys.TAB).perform();
		}
		
		System.out.println("Test has started ....");		
	
		actions
		.sendKeys("Bret").pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys("Hart").pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys(CommonUtilities.generateBrandNewEmail()).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys("34563455").pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys("ggggg").pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys("ggggg").pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.SPACE).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.TAB).pause(Duration.ofSeconds(5))
		.sendKeys(Keys.ENTER).build().perform();
		
		String actualMessage=driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
		Assert.assertEquals(actualMessage,"Your Account Has Been Created!00");//Test failed intentionally

	}
	
	
}
