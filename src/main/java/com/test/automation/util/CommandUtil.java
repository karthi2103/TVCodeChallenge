package com.test.automation.util;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;

import java.util.List;

@Slf4j
public final class CommandUtil {

  public static boolean clickOnElement(WebDriver driver, WebElement element){
    try {
      element.click();
    } catch (Exception e) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        try {
          executor.executeScript("arguments[0].click();", element);
        }
        catch (Exception ex){
          log.error("Exception occured while clicking the element"+ element +" due to " +ex);
          return false;
        }
    }
    return true;
  }

  public static boolean isElementPresent(WebElement ele) {
    try {
      return ele.isDisplayed();
    } catch (NoSuchElementException e) {
      log.error("Element not found on the page. Exception seen - " + e.getMessage());
      return false;
    }
  }

  public static boolean isListOfELementPresent(List<WebElement> elementList){
      return null != elementList && !elementList.isEmpty() && isElementPresent(elementList.get(0));
  }

  public static boolean isElementPresent(WebDriver driver, By by){
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }
  }


