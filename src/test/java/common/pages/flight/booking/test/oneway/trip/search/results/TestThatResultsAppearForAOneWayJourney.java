package common.pages.flight.booking.test.oneway.trip.search.results;

import com.test.automation.Application;
import com.test.automation.pom.ClearTripFlightBookingPage;
import common.AbstractWebTest;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@ContextConfiguration(classes = Application.class)
public abstract class TestThatResultsAppearForAOneWayJourney extends AbstractWebTest {
  public abstract String getFromLocation();
  public abstract String getToLocation();




  private ClearTripFlightBookingPage clearTripFlightBookingPage;


  @BeforeClass
  public void setup(){
    clearTripFlightBookingPage = new ClearTripFlightBookingPage(driver);
  }

  @Test(priority = 1, description = "check whether the driver and Page is setup")
  public void verifyClearTripFlightBookingPage(){
    Assert.assertNotNull(clearTripFlightBookingPage);
  }

  @Test(priority = 2, description = "check whether one way radio button is enabled")
  public void testThatOneWayRadioButtonIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isOneWayTripButtonEnabled());
  }

  @Test(priority = 3, description = "check whether round trip radio button is enabled")
  public void testThatRoundTripRadioButtonIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isRoundTripButtonEnabled());
  }

  @Test(priority = 4, description = "check whether multi city radio button is enabled")
  public void testThatMultiCityRadioButtonIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isMultiCityRadioButtonEnabled());
  }

  @Test(priority = 5, description = "check whether From location input is enabled")
  public void testThatFromLocationxInputTagIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isFromLocationPickerEnabled());
  }

  @Test(priority = 6, description = "check whether To Location input is enabled")
  public void testThatToLocationInputTagIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isToLocationPickerEnabled());
  }

  @Test(priority = 7, description = "check whether DatePicker is enabled")
  public void testThatDatePickerIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isDepartureDatePickerEnabled());
  }

  @Test(priority = 8, description = "check whether Adult Dropdown is enabled")
  public void testThatAdultDropDownIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isAdultsCountPickerEnabled());
  }

  @Test(priority = 9, description = "check whether click on one way radio button is successfull")
  public void testThatClickOnOneWayRadioButtonIsSuccessFull(){
      Assert.assertTrue(clearTripFlightBookingPage.clickOnOneWay());
  }

  @Test(priority = 10, description = "fill from location")
  public void testThatFromLocationIsFilledWithAutoCorrectResults(){
    Assert.assertTrue(clearTripFlightBookingPage.enterFromLocation(getFromLocation()));
  }

  @Test(priority = 11, description = "fill To location")
  public void testThatToLocationIsFilledWithAutoCorrect(){
    Assert.assertTrue(clearTripFlightBookingPage.enterToLocation(getToLocation()));
  }

  @Test(priority = 12, description = "Enter departure date")
  public void testThatDeprtureDateCalenderIsWorking(){
    Assert.assertTrue(clearTripFlightBookingPage.pickFirstDateFromCalender());
  }

  @Test(priority = 13, description = "Check whether submit button is enabled")
  public void testThatSubmitButtonIsEnabled(){
    Assert.assertTrue(clearTripFlightBookingPage.isSearchButtonEnabled());
  }

  @Test(priority = 14, description = "Click on submit button")
  public void testThatClickOnSubmitButtonLeadsToResultsPage(){
    Assert.assertTrue(clearTripFlightBookingPage.verifySearchResult());
  }

  @AfterClass
  public void tearDown(){
    driver.quit();
  }


}
