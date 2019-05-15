package common.pages.hotel.booking.search.result.abstractexecutor;

import com.test.automation.pom.ClearTripFlightBookingPage;
import com.test.automation.pom.ClearTripHotelBookingPage;
import common.AbstractWebTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public abstract class TestThatSearchResultsAppearForHotels extends AbstractWebTest {

  public abstract String getHotelCityLocation();

  private ClearTripHotelBookingPage clearTripHotelBookingPage;
  @BeforeClass
  public void setup(){
    clearTripHotelBookingPage = new ClearTripHotelBookingPage(driver);
  }

  @Test(priority = 1, description = "Verify whether Hotel Page Driver is loaded")
  public void verifyHotelsPageDriver(){
    Assert.assertNotNull(clearTripHotelBookingPage);
  }

  @Test(priority = 2, description = "Enter City where Hotels are to be searched", dependsOnMethods = "verifyHotelsPageDriver")
  public void testThatAutoCompleteDetectsTheEnteredCityInCityInputBox(){
    Assert.assertTrue(clearTripHotelBookingPage.enterCityWhereHotelisToBeBooked(getHotelCityLocation()));
  }

  @Test(priority = 3, description = "Verify submit button result", dependsOnMethods = "verifyHotelsPageDriver")
  public void testThatSubmitRedirectsToResultsPage(){
    Assert.assertTrue(clearTripHotelBookingPage.verifySearchResultAfterClickingOnSubmitButton());
  }

  @AfterClass
  public void tearDown(){
    driver.quit();
  }




}
