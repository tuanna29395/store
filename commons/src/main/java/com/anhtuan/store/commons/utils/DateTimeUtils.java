package com.anhtuan.store.commons.utils;

import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static Date addTime(Date date, Integer hour, Integer minute, Integer second) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date.getTime());
        cal.add(Calendar.HOUR, hour);
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.SECOND, second);

        return cal.getTime();
    }

}
