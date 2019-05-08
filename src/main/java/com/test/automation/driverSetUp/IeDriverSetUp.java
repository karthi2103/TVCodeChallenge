package com.test.automation.driverSetUp;

import com.test.automation.enums.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.stereotype.Service;

@Service
public class IeDriverSetUp implements CrossBrowserSetUp {

  @Override
  public BrowserName getBrowser() {
    return BrowserName.IE;
  }

  @Override
  public WebDriver getWebDriver() {
    WebDriverManager.iedriver().setup();
    return new InternetExplorerDriver();
  }
}
