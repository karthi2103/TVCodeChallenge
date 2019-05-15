package common.pages.signin.error.message.test.abstractexecutor;

import com.test.automation.pom.ClearTripSignInModal;
import common.AbstractWebTest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public abstract class TestErrorMessageForUnsuccessfulSignIn extends AbstractWebTest {

  public abstract String getUserName();
  public abstract String getPassword();
  public abstract String getErrorMessage();

  private ClearTripSignInModal clearTripSignInModal;

  @BeforeClass
  public void setup(){
    clearTripSignInModal = new ClearTripSignInModal(driver);
  }

  @Test(priority = 1, description = "Check for clear trip signin driver")
  public void verifySignInMedal(){
    Assert.assertNotNull(clearTripSignInModal);
  }

  @Test(priority = 2, description = "Check for presence of enter user name input box")
  public void testThatUserNameInputIsPresent(){
    Assert.assertTrue(clearTripSignInModal.isUserNameInputBoxEnabled());
  }

  @Test(priority = 2, description = "Check whether password input box is present")
  public void testThatPasswordInputIsPresent(){
    Assert.assertTrue(clearTripSignInModal.isPasswordInputBoxEnabled());
  }

  @Test(priority = 3, description = "Check whether forgot password link is present")
  public void testThatForgotPasswordIsPresent(){
    Assert.assertTrue(clearTripSignInModal.isForgotMyPasswordEnabled());
  }

  @Test(priority = 4, description = "Check whether remember password radio is present and enabled")
  public void testThatRememberMeRadioIsPresent(){
    Assert.assertTrue(clearTripSignInModal.isRememberPassWordEnabled());
  }

  @Test(priority = 5, description = "Enter user name")
  public void testThatUserNameFieldIsFilled(){
    Assert.assertEquals(clearTripSignInModal.enterUserName(getUserName()),getUserName());
  }

  @Test(priority = 6, description = "Enter passowrd")
  public void testThatPasswordFieldIsFilled(){
    Assert.assertEquals(clearTripSignInModal.enterPassword(getPassword()), getPassword());
  }

  @Test(priority = 7, description = "verify signin button")
  public void testThatSignInButtonIsClicked(){
    Assert.assertTrue(clearTripSignInModal.clickOnSignInButton());
  }

  @Test(priority = 8, description = "verify error message")
  public void verifyErrorMessage(){
    Assert.assertTrue(clearTripSignInModal.isErrorMessageSeen()
    && clearTripSignInModal.getErrorMessage().equals(getErrorMessage()));
  }

  @AfterClass
  public void tearDown(){
    driver.quit();
  }
}
