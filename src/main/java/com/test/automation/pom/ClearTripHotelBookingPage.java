package com.test.automation.pom;

import com.test.automation.util.CommandUtil;
import com.test.automation.util.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClearTripHotelBookingPage {
  private WebDriver webDriver;

  @FindBy(id = "Tags")
  private WebElement hotelCity;

  @FindBy(css = "a.ui-state-default")
  private WebElement datePicker;

  @FindBy(id = "travellersOnhome")
  private WebElement numberOfRoomsAndTravellersDropDown;

  @FindBy(id = "SearchHotelsButton")
  private WebElement searchButton;

  private ClearTripFlightBookingPage clearTripFlightBookingPage;

  public ClearTripHotelBookingPage(WebDriver webDriver) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver,this);
    clearTripFlightBookingPage = new ClearTripFlightBookingPage(webDriver);
    clearTripFlightBookingPage.getToHotelsPage(webDriver);
    WaitUtil.waitForPageLoad(webDriver);
  }


  public boolean isHotelCityInputBoxEnabled(){
    return CommandUtil.isElementPresent(hotelCity) && hotelCity.isEnabled();
  }

  public boolean isCheckInDateBoxPresent(){
    return CommandUtil.isElementPresent(datePicker);
  }

  public boolean isCheckOutDateBoxPresent(){
    return CommandUtil.isElementPresent(datePicker);
  }

  public boolean isTravellersDropDownEnabled(){
    return CommandUtil.isElementPresent(numberOfRoomsAndTravellersDropDown) && numberOfRoomsAndTravellersDropDown.isEnabled();
  }

  public boolean isSearchButtonEnabled(){
    return CommandUtil.isElementPresent(searchButton) && searchButton.isEnabled();
  }

  public boolean enterCityWhereHotelisToBeBooked(String city){
    hotelCity.clear();
    hotelCity.sendKeys(city);
    return CommandUtil.handleAutoCompletion(By.xpath("//ul[@id='ui-id-1']//li//a"),webDriver);
  }

  public boolean pickCheckInDate(){
    WebElement datePicker = webDriver.findElement(By.cssSelector("a.ui-state-default"));
    return CommandUtil.isElementPresent(datePicker) && CommandUtil.clickOnElement(webDriver,datePicker);
  }

  public boolean pickCheckOutDate(){
    WebElement datePicker = webDriver.findElement(By.cssSelector("a.ui-state-default"));
    return CommandUtil.isElementPresent(datePicker) && CommandUtil.clickOnElement(webDriver,datePicker);
  }

  public String pickNumberOfRoomAndTravellers(String numberOfTravellersAndRoom){
    return CommandUtil.fillDropDownWithValues(numberOfTravellersAndRoom, numberOfRoomsAndTravellersDropDown);
  }


  public boolean verifySearchResultAfterClickingOnSubmitButton(){
    return isSearchButtonEnabled() && CommandUtil.clickOnElement(webDriver,searchButton)
            &&  WaitUtil.waitForTheElementToBeSeenOnPage(webDriver, By.className("searchSummary"), 5)
            && CommandUtil.isElementPresent(webDriver,By.className("searchSummary"));
  }
}
