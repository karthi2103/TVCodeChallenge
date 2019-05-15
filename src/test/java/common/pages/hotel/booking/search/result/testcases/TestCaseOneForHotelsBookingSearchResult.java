package common.pages.hotel.booking.search.result.testcases;

import common.pages.hotel.booking.search.result.abstractexecutor.TestThatSearchResultsAppearForHotels;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class TestCaseOneForHotelsBookingSearchResult extends TestThatSearchResultsAppearForHotels {

  @BeforeClass
  public void setup(){
    Optional<WebDriver> webDriverOptional = pageSetup.configureWebDriver();
    if(webDriverOptional.isPresent()){
      driver = webDriverOptional.get();
      super.setup();
    }
    else throw new IllegalStateException("Driver initialisation failed, check browser configurations");
  }


  @Override
  public String getHotelCityLocation() {
    return "Indira Nagar";
  }
}
