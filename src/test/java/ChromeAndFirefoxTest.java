import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeAndFirefoxTest {

  private WebDriver driverOne;
  private WebDriver driverTwo;

  @BeforeTest
  public void setup(){
    WebDriverManager.firefoxdriver().setup();
    WebDriverManager.chromedriver().setup();
    driverOne=new ChromeDriver();
    driverTwo=new FirefoxDriver();
  }

  @Test
  public void chromeAndFirefoxTest() throws InterruptedException {
    driverOne.get("https://testng.org/");
    driverTwo.get("https://testng.org/");
    Thread.sleep(3000);
    Assert.assertEquals(driverOne.getTitle(),driverTwo.getTitle());
  }

  @AfterTest
  public void tearDown(){
    if(driverOne!=null) driverOne.quit();
    if(driverTwo!=null) driverTwo.quit();
  }

}
