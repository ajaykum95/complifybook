package com.abhaempire.complifybook.utils.validation;

import java.util.Objects;
import java.util.regex.Pattern;

public class MobileValidator {
 private static final String regex = "^(\\+91|91|0)?[6-9]\\d{9}$";

 public static boolean isValidMobile(String mobileNumber) {
  if (Objects.isNull(mobileNumber) || mobileNumber.isBlank())
   return false;
  return Pattern.compile(regex)
        .matcher(mobileNumber)
        .matches();
 }

}
