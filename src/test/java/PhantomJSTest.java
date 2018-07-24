import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PhantomJSTest {

  private WebDriver driver;

  @BeforeTest
  public void setUp(){
    PhantomJsDriverManager.getInstance().setup();
    driver=new PhantomJSDriver();
  }

  @Test
  public void phantomTest(){
    driver.get("https://testng.org/");
    Assert.assertEquals("TestNG - Welcome",driver.getTitle());
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }
}
