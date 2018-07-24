import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.*;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.concurrent.Executors.newFixedThreadPool;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import javax.swing.*;

public class ChromePerformanceTest {

  Logger logger=LoggerFactory.getLogger(ChromePerformanceTest.class);

  //Maximum number of browsers
  private static final int NUMBER_OF_BROWSERS=5;

  //First create an ArrayList that would hold all the browsers
  private List<WebDriver> driverList=new ArrayList<>(NUMBER_OF_BROWSERS);

  //Define a rule for all the Junit test methods to reuse
  @Rule
  public ErrorCollector errorCollector=new ErrorCollector();

  @BeforeClass
  public static void env(){
    ChromeDriverManager.getInstance().setup();
  }

  @Before
  public void setUp(){
    for(int i=0;i<NUMBER_OF_BROWSERS;i++){
      driverList.add(new ChromeDriver());
    }
  }

  @Test
  public void performanceTest() throws InterruptedException {
    ExecutorService executorService=newFixedThreadPool(NUMBER_OF_BROWSERS);
    final CountDownLatch latch=new CountDownLatch(NUMBER_OF_BROWSERS);

    for(final WebDriver driver:driverList){
      executorService.execute(new Runnable() {
        @Override
        public void run() {
          try{
            logger.info("Inside try method of Runnable class");
            singleTestDriver(driver);
          }catch (Exception e){
            logger.info("Collecting the error {}",e);
            errorCollector.addError(e);
          }finally {
            logger.info("Counting down...");
            latch.countDown();
          }
          }
        }
      );
    }

    latch.await();
    logger.info("Executor service shutting down...");
    executorService.shutdown();
  }

  private void singleTestDriver(WebDriver driver) throws InterruptedException {
    logger.info("Inside the test method...");
    driver.get("https://github.com");
    try{
      driver.manage().window().maximize();
    }catch (Exception e){
      e.printStackTrace();
    }
    By searchBox=By.cssSelector(".js-site-search-focus");
    driver.findElement(searchBox).sendKeys("wikia");
    driver.findElement(searchBox).sendKeys(Keys.ENTER);
    logger.info("Test done...");
    Thread.sleep(3000);
  }

  @After
  public void tearDown(){
    logger.info("Now quitting all drivers...");
    for(int i=0;i<NUMBER_OF_BROWSERS;i++){
      logger.info("Quitting driver {}",i);
      driverList.get(i).quit();
    }
  }

}
