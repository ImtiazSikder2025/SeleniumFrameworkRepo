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

import com.utils.CommonUtilities;

public class TC_RF_006 {

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
	
	
	
	
	
	
	//Verify Registering an account when 'No' option is selected for NewsLetter field
	@Test
	public void verifyNoLetterIsSelectedForNewsLetter() {
		
		driver.findElement(By.name("firstname")).sendKeys("Bret");
		driver.findElement(By.name("lastname")).sendKeys("Hart");
		driver.findElement(By.name("email")).sendKeys(CommonUtilities.generateBrandNewEmail());
		driver.findElement(By.name("telephone")).sendKeys("345544544");
		driver.findElement(By.name("password")).sendKeys("pppppp");
		driver.findElement(By.name("confirm")).sendKeys("pppppp");
		driver.findElement(By.name("agree")).click();
		
		//Clicking on 'No' option for NewsLetter
		driver.findElement(By.xpath("//input[@name='newsletter' and @value='0']")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String expectedText="Your Account Has Been Created!";
		String actualText=driver.findElement(By.xpath("//h1[text()='Your Account Has Been Created!']")).getText();
		Assert.assertEquals(actualText, expectedText);
		
		boolean flag=driver.findElement(By.xpath("//a[text()='Success']")).isDisplayed();
		Assert.assertTrue(flag);
		
		
	}
		
	
	
}
