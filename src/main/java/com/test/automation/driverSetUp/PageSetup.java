package com.test.automation.driverSetUp;

import com.test.automation.driverSetUp.CrossBrowserSetUp;
import com.test.automation.enums.BrowserName;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@NoArgsConstructor
@Service
public class PageSetup {
  @Value("${browserName}")
  private String browserName;

  private Map<BrowserName,CrossBrowserSetUp> browserToSetUpImplementaionMap;

  @Autowired
  public PageSetup(List<CrossBrowserSetUp> crossBrowserSetUpImplementaions) {
    this.browserToSetUpImplementaionMap = crossBrowserSetUpImplementaions.stream()
            .collect(Collectors.toMap(CrossBrowserSetUp::getBrowser,Function.identity()));
  }

  public Optional<WebDriver> configureWebDriver(){
    Map<String, BrowserName> browserEnumToStringMapping = BrowserName.getBrowserEnumToStringMapping();
    if(null != browserEnumToStringMapping && !browserEnumToStringMapping.isEmpty()
            && null != browserEnumToStringMapping.get(browserName)){
      return Optional.ofNullable(browserToSetUpImplementaionMap.get(browserEnumToStringMapping.get(browserName)).getWebDriver());
    }
    else return Optional.empty();
  }


}
