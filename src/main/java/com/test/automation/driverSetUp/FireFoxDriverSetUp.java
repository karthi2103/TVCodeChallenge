package com.test.automation.driverSetUp;

import com.test.automation.enums.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class FireFoxDriverSetUp implements CrossBrowserSetUp {
  @Override
  public BrowserName getBrowser() {
    return BrowserName.FF;
  }

  @Override
  public WebDriver getWebDriver() {
    WebDriverManager.firefoxdriver().setup();
    return new FirefoxDriver();
  }
}
