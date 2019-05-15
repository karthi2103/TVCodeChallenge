package com.test.automation.pom;

import com.test.automation.util.CommandUtil;
import com.test.automation.util.WaitUtil;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ClearTripSignInModal {

  private WebDriver driver;

  @FindBy(id = "email")
  private WebElement userName;

  @FindBy(id = "password")
  private WebElement password;

  @FindBy(linkText = "I forgot my password")
  private WebElement forgotMyPassword;

  @FindBy(id = "signuplink")
  private WebElement signupButton;

  @FindBy(id = "signInButton")
  private WebElement signInButton;

  @FindBy(id = "persistent_login")
  private WebElement rememberMe;

  public ClearTripSignInModal(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver,this);
    ClearTripFlightBookingPage clearTripFlightBookingPage = new ClearTripFlightBookingPage(driver);
    clearTripFlightBookingPage.getToSignInModal();
    switchToSignInModalForm();
  }

  public boolean isUserNameInputBoxEnabled(){
    return CommandUtil.isElementPresent(userName) && userName.isEnabled();
  }

  public boolean isPasswordInputBoxEnabled(){
    return CommandUtil.isElementPresent(password) && password.isEnabled();
  }

  public boolean isForgotMyPasswordEnabled(){
    return CommandUtil.isElementPresent(forgotMyPassword) && forgotMyPassword.isEnabled();
  }

  public boolean isRememberPassWordEnabled(){
    return CommandUtil.isElementPresent(rememberMe) && rememberMe.isEnabled();
  }

  public boolean isSignUpButtonIsEnabled(){
    return CommandUtil.isElementPresent(signupButton) && signupButton.isEnabled();
  }

  public String enterUserName(String user){
    userName.clear();
    userName.sendKeys(user);
    return userName.getAttribute("value");
  }

  public String enterPassword(String passwd){
    password.clear();
    password.sendKeys(passwd);
    return password.getAttribute("value");
  }

  public boolean clickOnSignInButton(){
    return CommandUtil.clickOnElement(driver,signInButton);
  }


  public String getErrorMessage(){
    return driver.findElement(By.id("errors1")).getText();
  }

  public boolean isErrorMessageSeen(){
    return WaitUtil.waitForTheElementToBeSeenOnPage(driver, By.id("errors1"),10);
  }

  private void switchToSignInModalForm(){
    int iframeSize = driver.findElements(By.tagName("iframe")).size();

    for (int i=0; i< iframeSize; i++){
      driver.switchTo().frame(i);
      try{
        driver.findElement(By.id("signInButton")).isDisplayed();
        return;
      }
      catch (NoSuchElementException e){
        driver.switchTo().parentFrame();
      }
    }
  }


}
