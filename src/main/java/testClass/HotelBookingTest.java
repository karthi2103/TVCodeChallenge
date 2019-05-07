package testClass;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class HotelBookingTest extends AbstractTestCaseExecutor{

    private static final int WAIT_TIME_IN_SEC = 10;
    private static final String TEST_URL = "https://www.cleartrip.com/";
    private static final String SEARCH_LOCATION = "Indiranagar, Bangalore";
    private static final String TRAVELLER_TYPE_KEY = "1 room, 2 adults";
    private WebDriver driver = configureDriver();

    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @FindBy(id = "CheckInDate")
    private WebElement checkInDateSelctor;

    @FindBy(id = "CheckOutDate")
    private WebElement checkOutDateSelector;

    public HotelBookingTest() {
        PageFactory.initElements(driver,this);
    }

    @Test
    public void shouldBeAbleToSearchForHotels() {

        driver.get(TEST_URL);
        hotelLink.click();

        localityTextBox.sendKeys(SEARCH_LOCATION);
        waitForTheElementToBeSeenOnPage(driver, By.xpath("//ul[@id='ui-id-1']//li//a"), WAIT_TIME_IN_SEC);
        List<WebElement> localitySearchOptions = driver.findElements(By.xpath("//ul[@id='ui-id-1']//li//a"));
        localitySearchOptions.get(0).click();

        checkInDateSelctor.click();
        driver.findElement(By.cssSelector("a.ui-state-default")).click();
        checkOutDateSelector.click();
        driver.findElement(By.cssSelector("a.ui-state-default")).click();

        new Select(travellerSelection).selectByVisibleText(TRAVELLER_TYPE_KEY);
        searchButton.click();

        waitForTheElementToBeSeenOnPage(driver, By.className("searchSummary"), WAIT_TIME_IN_SEC);
        //verify that result appears for the provided journey search
        Assert.assertTrue(isElementPresent(By.className("searchSummary"), driver));

        driver.quit();

    }


}
