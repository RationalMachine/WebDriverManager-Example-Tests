import static org.apache.commons.io.FileUtils.copyFile;
import static org.openqa.selenium.OutputType.FILE;

import io.github.bonigarcia.wdm.*;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class ChromeTest {

  private WebDriver driver;

  @BeforeTest
  public void startUp() {

    //ChromeDriver
    /*
    WebDriverManager.chromedriver().setup();
    ChromeOptions options=new ChromeOptions();
    options.addArguments("disable-infobars");
    options.addArguments("--headless");
    options.addArguments("start-maximized");
    driver=new ChromeDriver(options);
    ChromeOptions options=new ChromeOptions();
    options.addArguments("--headless");
    ChromeDriverManager.getInstance().setup();*/
    ChromeDriverManager.getInstance().setup();
    driver=new ChromeDriver();
  }

  @Test
  public void test01() throws InterruptedException, IOException {
    driver.get("https://testng.org/");
    Thread.sleep(5000);
    File scfile=((TakesScreenshot)driver).getScreenshotAs(FILE);
    copyFile(scfile,new File("screenshot.png"));
    Thread.sleep(2000);
    Assert.assertEquals("TestNG - Welcome",driver.getTitle());
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }
}
