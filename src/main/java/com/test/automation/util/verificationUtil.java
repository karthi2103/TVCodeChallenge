package com.test.automation.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class verificationUtil {

  public static boolean isStringAnInteger(String val){
    try{
      Integer.parseInt(val);
    }
    catch (NullPointerException | NumberFormatException e){
      log.error(val + " is not a valid integer number");
      return false;
    }
    return true;
  }
}
