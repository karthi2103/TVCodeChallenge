package common;

import com.test.automation.enums.BrowserName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class FlightBookingTest extends AbstractTestCaseExecutor {
  private static final String TEST_URL = "https://www.cleartrip.com/";
  private static final String FROM_SEARCH_KEY = "Bangalore";
  private static final String DESTINATION_SEARCH_KEY = "Delhi";
  private static final int AUTO_COMPLETE_LOAD_TIME = 20;
  WebDriver driver;

  @FindBy(id = "OneWay")
  WebElement oneWayTrip;

  @FindBy(id="FromTag")
  WebElement tripStartLocation;

  @FindBy(id="ToTag")
  WebElement destinationLocation;

  @FindBy(css = "a.ui-state-default")
  WebElement tripDate;

  @FindBy(id = "SearchBtn")
  WebElement searchButton;

  @Autowired
  public void setup() {
    driver = configureDriver(BrowserName.CHROME);
    PageFactory.initElements(driver,this);
  }

  @Test
  public void testThatResultsAppearForAOneWayJourney() {

    driver.get(TEST_URL);
    waitForPageLoad(driver);

    oneWayTrip.click();

    tripStartLocation.clear();
    tripStartLocation.click();
    tripStartLocation.sendKeys(FROM_SEARCH_KEY);
    //wait for the auto complete options to appear for the origin

    waitForTheElementToBeSeenOnPage(driver, By.xpath("//ul[@id='ui-id-1']//li//a"), AUTO_COMPLETE_LOAD_TIME);
    List<WebElement> originOptions = driver.findElements(By.xpath("//ul[@id='ui-id-1']//li//a"));
    originOptions.get(0).click();

    destinationLocation.clear();
    destinationLocation.sendKeys(DESTINATION_SEARCH_KEY);

    //wait for the auto complete options to appear for the destination
    waitForTheElementToBeSeenOnPage(driver, By.xpath("//ul[@id='ui-id-2']//li//a"), AUTO_COMPLETE_LOAD_TIME);

    //select the first item from the destination auto complete list
    List<WebElement> destinationOptions = driver.findElements(By.xpath("//ul[@id='ui-id-2']//li//a"));
    destinationOptions.get(0).click();

    tripDate.click();

    //all fields filled in. Now click on search
    searchButton.click();

    waitForTheElementToBeSeenOnPage(driver, By.className("searchSummary"), AUTO_COMPLETE_LOAD_TIME);

    //verify that result appears for the provided journey search
    Assert.assertTrue(isElementPresent(By.className("searchSummary"), driver));

    //close the browser
    driver.quit();

  }


}
