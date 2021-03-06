package tests;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestNewInvalidEvent {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\school\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	@Test
	public void testNewRegEvent() throws Exception {
		driver.get("http://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:5000/users/login");
		Thread.sleep(1000);
		driver.findElement(By.id("email")).sendKeys("testing@test.com");
		Thread.sleep(1000);
		driver.findElement(By.id("password")).sendKeys("testing");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("add")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("EventNameID")).sendKeys("Automated Test Event");
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitID")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("urlID")).sendKeys("http://www.google.com");
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitID")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("datepicker")).sendKeys("11-20-2020");
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitID")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("StartTimeID")).sendKeys("14:00");
		Thread.sleep(1000);
		driver.findElement(By.id("StartTimeID")).clear();
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitID")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("grayout")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("SubmitID")).click();
		Thread.sleep(1000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}