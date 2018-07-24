import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import static java.awt.Toolkit.getDefaultToolkit;
import static javax.imageio.ImageIO.write;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FullScreenCaptureTest {

  private WebDriver driver;

  @BeforeTest
  public void setUp(){
    WebDriverManager.chromedriver().setup();
    driver=new ChromeDriver();
  }

  @Test
  public void fullCaptureTest() throws AWTException, IOException, InterruptedException {
    driver.get("https://testng.org/");
    Thread.sleep(3000);
    Robot robot=new Robot();
    Rectangle capture=new Rectangle(getDefaultToolkit().getScreenSize());
    BufferedImage image=robot.createScreenCapture(capture);
    write(image,"png",new File("FullSize.png"));
  }

  @AfterTest
  public void tearDown(){
    driver.quit();
  }

}
