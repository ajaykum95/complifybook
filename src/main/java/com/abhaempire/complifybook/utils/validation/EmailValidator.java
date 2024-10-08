package com.abhaempire.complifybook.utils.validation;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class EmailValidator {
 private static final String EMAIL_PATTERN_REGEX =
       "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_REGEX);

 public static boolean isValidEmail(String email) {
  if (Objects.isNull(email) || email.isBlank())
   return false;
  Matcher matcher = EMAIL_PATTERN.matcher(email);
  if (matcher.matches()) {
   try {
    InternetAddress internetAddress = new InternetAddress(email);
    internetAddress.validate();
    return true;
   } catch (AddressException ignored) {
   }
  }
  return false;
 }
}
