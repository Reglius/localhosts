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

public class TestRegister {
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
  public void testRegister() throws Exception {
    driver.get("http://ec2-3-137-149-170.us-east-2.compute.amazonaws.com:5000/");
    Thread.sleep(10000);
    driver.findElement(By.linkText("Register")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("name")).click();
    Thread.sleep(10000);
    driver.findElement(By.id("name")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("name")).sendKeys("Test Tester");
    Thread.sleep(10000);
    driver.findElement(By.id("email")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("email")).sendKeys("testing@testing.com");
    Thread.sleep(10000);
    driver.findElement(By.id("password")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("password")).sendKeys("testing");
    Thread.sleep(10000);
    driver.findElement(By.id("password2")).clear();
    Thread.sleep(10000);
    driver.findElement(By.id("password2")).sendKeys("testing");
    Thread.sleep(10000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
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
    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
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
