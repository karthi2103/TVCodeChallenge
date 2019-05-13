package com.test.automation.enums;

import lombok.Builder;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public enum BrowserName {
  CHROME("chrome"),
  FF("firefox"),
  IE("ie");

  private final String browserName;

  BrowserName(String browserName) {
    this.browserName = browserName;
  }

  public String getBrowserName(){
    return browserName;
  }

  public static Map<String,BrowserName> getBrowserEnumToStringMapping(){
    return Arrays.stream(BrowserName.values())
            .collect(Collectors.toMap(BrowserName::getBrowserName, browser -> browser));

  }


}
