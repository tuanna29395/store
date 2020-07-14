package com.anhtuan.store.commons.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static Date convertToDatabaseColumn(Date date) throws ParseException {
        if (date == null) {
            return null;
        }
        SimpleDateFormat out = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

        String date1 = out.format(date);
        return out.parse(date1);
    }

    public static Date defaultDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        Date defaultDate = format.parse("1/1/1753");
        return defaultDate;
    }

    public static String convertToString(Date date){

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }
}
