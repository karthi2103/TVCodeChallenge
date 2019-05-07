package testClass;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest extends AbstractTestCaseExecutor{

  private static final String TEST_URL = "https://www.cleartrip.com/";
  private static final String ERROR_MESSAGE = "There were errors in your submission";
  private WebDriver driver = configureDriver() ;

    @FindBy(linkText = "Your trips")
    private WebElement yourTrips;

    @FindBy(id = "SignIn")
    private WebElement signin;



  public SignInTest() {
    PageFactory.initElements(driver,this);
  }

  @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
        driver.get(TEST_URL);
        waitForPageLoad(driver);
        yourTrips.click();
        signin.click();

        // switch embedded signin html
        switchToSignInModal();
        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains(ERROR_MESSAGE));
        //switch back to parent frame
        driver.switchTo().parentFrame();
        driver.quit();
    }

    private void switchToSignInModal()  {
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

