import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirefoxTest {

  private WebDriver driver;

  @BeforeTest
  public void setup(){
    WebDriverManager.firefoxdriver().setup();
    driver=new FirefoxDriver();
  }

  @Test
  public void firefoxTest() throws InterruptedException {
    driver.get("https://testng.org");
    Thread.sleep(3000);
    Assert.assertEquals("TestNG - Welcome", driver.getTitle());
  }

  @Test
  public void tearDown(){
    driver.quit();
  }
}
