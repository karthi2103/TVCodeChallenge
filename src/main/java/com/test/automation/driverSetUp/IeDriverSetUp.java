package com.test.automation.driverSetUp;

import com.sun.javafx.PlatformUtil;
import com.test.automation.enums.BrowserName;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class IeDriverSetUp implements CrossBrowserSetUp {

  @Override
  public BrowserName getBrowser() {
    return BrowserName.IE;
  }

  @Override
  public WebDriver getWebDriver() {
    if(PlatformUtil.isWindows()) {
      WebDriverManager.iedriver().setup();
      return new InternetExplorerDriver();
    }
    log.error("IE can only be run in windows machine.");
    throw  new IllegalArgumentException("IE browser invoked in non-windows machine");
  }
}


