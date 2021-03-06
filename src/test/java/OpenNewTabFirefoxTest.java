import static java.awt.Toolkit.getDefaultToolkit;
import static javax.imageio.ImageIO.write;

import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;

@SuppressWarnings("Duplicates")
public class OpenNewTabFirefoxTest {

  private WebDriver driver;

  @BeforeTest
  public void setUp(){
    FirefoxDriverManager.getInstance().setup();
    driver=new FirefoxDriver();
  }

  @Test
  public void firefoxTabTest() throws InterruptedException, AWTException, IOException {
    ((JavascriptExecutor)driver).executeScript("window.open('about:blank','_blank');");
    Set<String> handles=driver.getWindowHandles();
    int newTab=handles.size()-1;
    driver.switchTo().window(handles.toArray()[newTab].toString());
    driver.get("https://testng.org/");
    Thread.sleep(3000);
    driver.switchTo().window(handles.toArray()[0].toString());
    driver.get("https://en.wikipedia.org/wiki/Main_Page");
    Thread.sleep(3000);
    Robot robot=new Robot();
    Rectangle cap=new Rectangle(getDefaultToolkit().getScreenSize());
    BufferedImage image=robot.createScreenCapture(cap);
    write(image,"png",new File("TabScreen.png"));
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }

}
