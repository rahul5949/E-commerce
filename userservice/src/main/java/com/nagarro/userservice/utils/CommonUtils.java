package com.nagarro.userservice.utils;

import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

    public static Date creatingExpiryDate(Date date, int duration){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, duration);
        return cal.getTime();
    }
}
