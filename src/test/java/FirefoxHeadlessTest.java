import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirefoxHeadlessTest {

  private WebDriver driver;

  @BeforeTest
  public void setup(){
    FirefoxOptions options=new FirefoxOptions();
    options.addArguments("--headless");
    WebDriverManager.firefoxdriver().setup();
    driver=new FirefoxDriver(options);
  }

  @Test
  public void headlessTest() throws InterruptedException {
    driver.get("https://testng.org/");
    Thread.sleep(3000);
    Assert.assertEquals("TestNG - Welcome", driver.getTitle());
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }

}
