package common.pages.signin.error.message.test.testcases;

import common.pages.signin.error.message.test.abstractexecutor.TestErrorMessageForUnsuccessfulSignIn;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class TestCaseOneForErrorMessageInSignIn extends TestErrorMessageForUnsuccessfulSignIn {

  @Override
  public String getUserName() {
    return "user";
  }

  @Override
  public String getPassword() {
    return "passwd";
  }

  @Override
  public String getErrorMessage() {
    return "There were errors in your submission\n" +
            "Your username should be a valid email address.";
  }

  @BeforeClass
  public void setup(){
    Optional<WebDriver> webDriverOptional = pageSetup.configureWebDriver();
    if(webDriverOptional.isPresent()){
      driver = webDriverOptional.get();
      super.setup();
    }
    else throw new IllegalStateException("Driver initialisation failed, check browser configurations");
  }
}
