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

public class TC_RF_11 {

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
	
	
	
	
	
	
	
	//Verify Registering an account by providing "Invalid Email" address
	@Test
	public void verifyRegisteringByProvidingInvalidEmail() throws Exception {
		
	//First validation == 'isikder'
		driver.findElement(By.name("firstname")).sendKeys("Bret");
		driver.findElement(By.name("lastname")).sendKeys("Hart");
		driver.findElement(By.name("email")).sendKeys("isikder");
		driver.findElement(By.name("telephone")).sendKeys("345544544");
		driver.findElement(By.name("password")).sendKeys("pppppp");
		driver.findElement(By.name("confirm")).sendKeys("pppppp");
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Thread.sleep(3000);
		
		if(browserName.equals("chrome")) {
			System.out.println("expected message FOR FIRST VALIDATION ****");
			System.out.println(driver.findElement(By.name("email")).getDomProperty("validationMessage"));
			Assert.assertEquals(driver.findElement(By.name("email")).getDomProperty("validationMessage"), "Please include an '@' in the email address. 'isikder' is missing an '@'.");
		}else if(browserName.equals("firefox")) {
			System.out.println("Printing expected message ****");
			System.out.println(driver.findElement(By.name("email")).getDomProperty("validationMessage"));
			Assert.assertEquals(driver.findElement(By.name("email")).getDomProperty("validationMessage"), "Please enter an email address.");
		}		
		
		
	//Second validation === 'isikder@'
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("isikder@");
		driver.findElement(By.xpath("//input[@type='submit']")).click();		

		if(browserName.equals("chrome")) {
			System.out.println("Expected message for === SECOND VALIDATION");
			System.out.println(driver.findElement(By.name("email")).getDomProperty("validationMessage"));
			Assert.assertEquals(driver.findElement(By.name("email")).getDomProperty("validationMessage"), "Please enter a part following '@'. 'isikder@' is incomplete.");
		}else if(browserName.equals("firefox")) {
			System.out.println("Expected message for === SECOND VALIDATION");
			System.out.println(driver.findElement(By.name("email")).getDomProperty("validationMessage"));
			Assert.assertEquals(driver.findElement(By.name("email")).getDomProperty("validationMessage"), "Please enter an email address.");
		}		
		
		
	//Third validation === 'isikder@gmail'
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("isikder@gmail");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		if(browserName.equals("chrome")) {
			System.out.println("Expected message for === THIRD VALIDATION");
			System.out.println(driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText());			
			String expectedMessageThird="E-Mail Address does not appear to be valid!";
			String actualMessageThird=driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
			Assert.assertEquals(actualMessageThird, expectedMessageThird);
		}else if(browserName.equals("firefox")) {
			System.out.println("Expected message for === THIRD VALIDATION");
			System.out.println(driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText());
			String actualMessageThree=driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
			String expectedMessageThree="E-Mail Address does not appear to be valid!";
			Assert.assertEquals(actualMessageThree, expectedMessageThree);
		}		
		
	
	//Fourth validation === 'isikder@gmail.'
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys("isikder@gmail.");
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		if(browserName.equals("chrome")) {
			System.out.println("Expected message for === FOURTH VALIDATION");
			System.out.println(driver.findElement(By.name("email")).getDomProperty("validationMessage"));
			String actualMessageFour=driver.findElement(By.name("email")).getDomProperty("validationMessage");
			String expectedMessageFour="'.' is used at a wrong position in 'gmail.'.";
			Assert.assertEquals(actualMessageFour, expectedMessageFour);
		}else if(browserName.equals("firefox")) {
			System.out.println("Expected message for === FOURTH VALIDATION");
			System.out.println(driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText());
			String actualMessageThree=driver.findElement(By.xpath("//div[text()='E-Mail Address does not appear to be valid!']")).getText();
			String expectedMessageThree="E-Mail Address does not appear to be valid!";
			Assert.assertEquals(actualMessageThree, expectedMessageThree);
		}
		
	}
		
	
}


/*
<class name="com.register.TC_RF_008"/>
<class name="com.register.TC_RF_007"/>
<class name="com.register.TC_RF_009"/>
<class name="com.register.TC_RF_004"/>
<class name="com.register.TC_RF_006"/>
<class name="com.register.TC_RF_005"/>
      <class name="com.register.TC_RF_001"/>
      <class name="com.register.TC_RF_10"/>
*/

/*POM
 * 



















<build>
		<pluginManagement><!-- lock down plugins versions to avoid using Maven
			defaults (may be moved to parent pom) -->
			<plugins>
				<!-- clean lifecycle, see
				https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
				<plugin>
					<artifactId>maven-clean-plugin</artifactId>
					<version>3.4.0</version>
				</plugin>
				<!-- default lifecycle, jar packaging: see
				https://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_jar_packaging -->
				<plugin>
					<artifactId>maven-resources-plugin</artifactId>
					<version>3.3.1</version>
				</plugin>
				<plugin>
					<artifactId>maven-compiler-plugin</artifactId>
					<version>3.13.0</version>
				</plugin>
				<plugin>
					<artifactId>maven-surefire-plugin</artifactId>
					<version>3.3.0</version>
				</plugin>
				<plugin>
					<artifactId>maven-jar-plugin</artifactId>
					<version>3.4.2</version>
				</plugin>
				<plugin>
					<artifactId>maven-install-plugin</artifactId>
					<version>3.1.2</version>
				</plugin>
				<plugin>
					<artifactId>maven-deploy-plugin</artifactId>
					<version>3.1.2</version>
				</plugin>
				<!-- site lifecycle, see
				https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
				<plugin>
					<artifactId>maven-site-plugin</artifactId>
					<version>3.12.1</version>
				</plugin>
				<plugin>
					<artifactId>maven-project-info-reports-plugin</artifactId>
					<version>3.6.1</version>
				</plugin>
			</plugins>
		</pluginManagement>
	</build>
</project>


*/