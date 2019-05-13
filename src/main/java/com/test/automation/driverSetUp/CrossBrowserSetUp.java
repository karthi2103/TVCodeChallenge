package com.test.automation.driverSetUp;

import com.test.automation.enums.BrowserName;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

@Service
public interface CrossBrowserSetUp {
   BrowserName getBrowser();
   WebDriver getWebDriver();

}
