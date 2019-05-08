package com.test.automation.driverSetUp;

import com.test.automation.enums.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class ChromeDriverSetUp implements CrossBrowserSetUp {

  @Override
  public BrowserName getBrowser() {
    return BrowserName.CHROME;
  }

  @Override
  public WebDriver getWebDriver() {
     WebDriverManager.chromedriver().setup();
     return new ChromeDriver();
  }
}
