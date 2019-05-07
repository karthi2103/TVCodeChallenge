package testClass;

import com.google.common.base.Function;
import com.sun.javafx.PlatformUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public abstract class AbstractTestCaseExecutor {
  private static final String CHROME_DRIVER = "webdriver.chrome.driver";
  private static int POLLING_TIME = 500;
  private static int PAGE_LOAD_TIME = 10;

  public WebDriver configureDriver(){
    setDriverPath();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    return new ChromeDriver(options);
  }

  private static void setDriverPath(){
    if (PlatformUtil.isMac()) {
      System.setProperty(CHROME_DRIVER, "chromedriver");
    }

    if (PlatformUtil.isWindows()) {
      System.setProperty(CHROME_DRIVER, "chromedriver.exe");
    }
    if (PlatformUtil.isLinux()) {
      System.setProperty(CHROME_DRIVER, "chromedriver_linux");
    }
  }

  public static void waitForTheElementToBeSeenOnPage(WebDriver driver, final By by, int waitTimeInSec){

    Wait<? extends WebDriver> wait = new FluentWait<>(driver)
            .withTimeout(waitTimeInSec, TimeUnit.SECONDS)
            .pollingEvery(POLLING_TIME, TimeUnit.MILLISECONDS)
            .ignoring(NoSuchElementException.class);

    wait.until(new Function<WebDriver, WebElement>() {
      @Nullable
      public WebElement apply(@Nullable WebDriver driver) {
        assert driver != null;
        return driver.findElement(by);

      }
    });
  }

  public static void waitForPageLoad(WebDriver driver){
    new WebDriverWait(driver, PAGE_LOAD_TIME).until(
            webDriver -> "complete".equals(((JavascriptExecutor) webDriver).executeScript("return document.readyState")));
  }

  public static boolean isElementPresent(By by, WebDriver driver) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
