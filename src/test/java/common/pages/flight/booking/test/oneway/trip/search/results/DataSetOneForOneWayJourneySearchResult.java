package common.pages.flight.booking.test.oneway.trip.search.results;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class DataSetOneForOneWayJourneySearchResult extends TestThatResultsAppearForAOneWayJourney {

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
  public String getFromLocation() {
    return "Bangalore";
  }

  @Override
  public String getToLocation() {
    return "Delhi";
  }

}
