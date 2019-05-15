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
public class ClearTripFlightBookingPage {

  @FindBy(id = "OneWay")
  private WebElement oneWayRadioButton;

  @FindBy(id = "RoundTrip")
  private WebElement roundTripRadioButton;

  @FindBy(id = "MultiCity")
  private WebElement multiCityRadioButton;

  @FindBy(id="FromTag")
  private WebElement fromLocationPicker;

  @FindBy(id="ToTag")
  private WebElement toLocationPicker;

  @FindBy(css = "a.ui-state-default")
  private WebElement departureDatePicker;

  @FindBy(id = "Adults")
  private WebElement adultsCountDropDown;

  @FindBy(id = "Childrens")
  private WebElement childrenCountDropDown;

  @FindBy(id = "Infants")
  private WebElement infantsCountDropDown;

  @FindBy(id = "SearchBtn")
  private WebElement searchFlightsButton;

  @FindBy(linkText = "Hotels")
  private WebElement hotelsLink;

  @FindBy(linkText = "Your trips")
  private WebElement yourTrips;

  @FindBy(id = "SignIn")
  private WebElement signin;


  private WebDriver driver;

  public ClearTripFlightBookingPage(WebDriver webDriver ) {
    this.driver = webDriver;
    PageFactory.initElements(driver, this);
    driver.get("https://www.cleartrip.com/");
  }

  public boolean isOneWayTripButtonEnabled() {
    return CommandUtil.isElementPresent(oneWayRadioButton) && oneWayRadioButton.isEnabled();
  }

  public boolean isRoundTripButtonEnabled() {
    return CommandUtil.isElementPresent(roundTripRadioButton) && roundTripRadioButton.isEnabled();
  }

  public boolean isMultiCityRadioButtonEnabled() {
    return CommandUtil.isElementPresent(multiCityRadioButton) && multiCityRadioButton.isEnabled();
  }

  public boolean isFromLocationPickerEnabled() {
    return CommandUtil.isElementPresent(fromLocationPicker) && fromLocationPicker.isEnabled();
  }

  public boolean isToLocationPickerEnabled() {
    return CommandUtil.isElementPresent(toLocationPicker) && toLocationPicker.isEnabled();
  }


  public boolean isAdultsCountPickerEnabled() {
    return CommandUtil.isElementPresent(adultsCountDropDown);
  }

  public boolean isSearchButtonEnabled() {
    return CommandUtil.isElementPresent(searchFlightsButton) && searchFlightsButton.isEnabled();
  }

  public boolean isYourTripSectionEnabled(){
    return CommandUtil.isElementPresent(yourTrips) && yourTrips.isEnabled();
  }

  public boolean isSignInButtonEnabled(){
    return CommandUtil.clickOnElement(driver,yourTrips) && CommandUtil.isElementPresent(signin) && signin.isEnabled();
  }

  public boolean clickOnOneWay() {
    CommandUtil.clickOnElement(driver, oneWayRadioButton);
    return oneWayRadioButton.isSelected();
  }

  public boolean clickOnRoundTrip() {
    CommandUtil.clickOnElement(driver, roundTripRadioButton);
    return roundTripRadioButton.isSelected();
  }

  public boolean clickOnMultiCityRadio() {
    CommandUtil.clickOnElement(driver, multiCityRadioButton);
    return multiCityRadioButton.isSelected();
  }

  public boolean enterFromLocation(String fromLocation) {
    fromLocationPicker.clear();
    fromLocationPicker.click();
    fromLocationPicker.sendKeys(fromLocation);
    return CommandUtil.handleAutoCompletion(By.xpath("//ul[@id='ui-id-1']//li//a"), driver);
  }



  public boolean enterToLocation(String toLocation) {
    toLocationPicker.clear();
    toLocationPicker.click();
    toLocationPicker.sendKeys(toLocation);
    return CommandUtil.handleAutoCompletion(By.xpath("//ul[@id='ui-id-2']//li//a"), driver);
  }

  public boolean pickFirstDateFromCalender() {
    return CommandUtil.clickOnElement(driver,departureDatePicker);
  }

  public boolean clickOnSearchButton(){
    return CommandUtil.clickOnElement(driver,searchFlightsButton);
  }

  public boolean verifySearchResult(){
    return clickOnSearchButton()
            && WaitUtil.waitForTheElementToBeSeenOnPage(driver, By.className("searchSummary"), 5)
            && CommandUtil.isElementPresent(driver,By.className("searchSummary"));
  }

  public String selectNumberOfChildrenInTravel(String numberOfChild){
    return CommandUtil.fillDropDownWithValues(numberOfChild, childrenCountDropDown);
  }

  public String selectNumberOfAdultsInTravel(String numberOfAdults){
    return CommandUtil.fillDropDownWithValues(numberOfAdults, adultsCountDropDown);
  }

  public String selectNumberOfInfantsInTravel(String numberOfInfants){
    return CommandUtil.fillDropDownWithValues(numberOfInfants, infantsCountDropDown);
  }



  public void  getToHotelsPage(WebDriver driver){
     CommandUtil.clickOnElement(driver,hotelsLink);
  }

  public void getToSignInModal(){
    CommandUtil.clickOnElement(driver,yourTrips);
    CommandUtil.clickOnElement(driver,signin);
  }

}

