package com.abhaempire.complifybook.utils;

import com.abhaempire.complifybook.configs.security.UserDetailsImpl;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;

public class Utils {
    public static LocalDate convertToLocalDate(Date date) {
        if (Objects.isNull(date)){
            return null;
        }
        ZoneId zoneId = ZoneId.of(AppConstant.IST_TIMEZONE);
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        return zonedDateTime.toLocalDate();
    }

    public static String getUserId(UserDetailsImpl loggedInUser) {
        return Objects.nonNull(loggedInUser) ? loggedInUser.getId().toString() : "0";
    }
}
