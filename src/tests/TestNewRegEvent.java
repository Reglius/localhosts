package tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestNewRegEvent {
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
    driver.get("http://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:5000/");
    Thread.sleep(10000);
    driver.findElement(By.linkText("Login")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("email")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("email")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("email")).sendKeys("testing@test.com");
    Thread.sleep(10000);
    driver.findElement(By.id("password")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("password")).sendKeys("testing");
    Thread.sleep(10000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("add")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("EventNameID")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("EventNameID")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("EventNameID")).sendKeys("Test Event");
    Thread.sleep(10000);
    driver.findElement(By.id("urlID")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("urlID")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("urlID")).sendKeys("www.google.com");
    Thread.sleep(10000);
    driver.findElement(By.id("datepicker")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("datepicker")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("datepicker")).sendKeys("2020-12-25");
    Thread.sleep(10000);
    driver.findElement(By.id("datepicker")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("StartTimeID")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("StartTimeID")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("//div[@id='EventDiv']/div/div[2]/form/div")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("StartTimeID")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("StartTimeID")).sendKeys("12:00");
    Thread.sleep(10000);
    driver.findElement(By.name("dataSubmit")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("SubmitID")).click();
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
