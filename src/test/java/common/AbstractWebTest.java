package common;

import com.test.automation.Application;
import com.test.automation.config.PropertiesInitialiser;
import com.test.automation.driverSetUp.PageSetup;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = Application.class, initializers = PropertiesInitialiser.class)
public abstract class AbstractWebTest extends AbstractTestNGSpringContextTests {
  @Autowired
  protected PageSetup pageSetup;
  protected WebDriver driver;
}
