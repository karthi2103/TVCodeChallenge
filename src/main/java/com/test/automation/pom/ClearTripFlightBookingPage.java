package com.test.automation.pom;

import com.test.automation.enums.BrowserName;
import com.test.automation.util.CommandUtil;
import com.test.automation.util.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.springframework.stereotype.Service;

import java.util.List;

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

  public boolean isDepartureDatePickerEnabled() {
    return CommandUtil.isElementPresent(departureDatePicker) && departureDatePicker.isEnabled();
  }

  public boolean isAdultsCountPickerEnabled() {
    return CommandUtil.isElementPresent(adultsCountDropDown);
  }

  public boolean isSearchButtonEnabled() {
    return CommandUtil.isElementPresent(searchFlightsButton) && searchFlightsButton.isEnabled();
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
    fromLocationPicker.sendKeys(fromLocation);
    return handleAutoCompletion(By.xpath("//ul[@id='ui-id-1']//li//a"));
  }

  private boolean handleAutoCompletion(By by){
    WaitUtil.waitForTheElementToBeSeenOnPage(driver, by, 5);
    List<WebElement> autoCompleteResults = driver.findElements(by);
    return !autoCompleteResults.isEmpty() && CommandUtil.clickOnElement(driver,autoCompleteResults.get(0));
  }

  public boolean enterToLocation(String toLocation) {
    toLocationPicker.clear();
    toLocationPicker.sendKeys(toLocation);
    return handleAutoCompletion(By.xpath("//ul[@id='ui-id-2']//li//a"));
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

  public String selectNumberOfChildrenInTravel(int numberOfChild){
    Select childrenSelectElement = new Select(childrenCountDropDown);
    childrenSelectElement.selectByVisibleText(Integer.toString(numberOfChild));
    return childrenSelectElement.getFirstSelectedOption().getText();
  }

  public String selectNumberOfAdultsInTravel(int numberOfAdults){
    Select adultTravellersSelectElement = new Select(adultsCountDropDown);
    adultTravellersSelectElement.selectByVisibleText(Integer.toString(numberOfAdults));
    return adultTravellersSelectElement.getFirstSelectedOption().getText();
  }

  public String selectNumberOfInfantsInTravel(int numberOfInfants){
    Select infantTravellersElement = new Select(infantsCountDropDown);
    infantTravellersElement.selectByVisibleText(Integer.toString(numberOfInfants));
    return infantTravellersElement.getFirstSelectedOption().getText();
  }

}

