import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ChromeHeadLessTest {

  private WebDriver driver;

  @BeforeTest
  public void setup(){
    ChromeOptions options=new ChromeOptions();
    options.addArguments("--headless");
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver(options);
  }

  @Test
  public void headlessTest(){
    driver.get("https://testng.org/");
    Assert.assertEquals("TestNG - Welcome",driver.getTitle());
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }

}
