package com.database_integration.App_Database.registration.token;

import java.util.Calendar;
import java.util.Date;

public class TokenExpirationTime {
    private static final int EXPIRATION_TIME =10;
    public static Date getExpirationTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.set(Calendar.MINUTE, EXPIRATION_TIME);
        return new Date(calendar.getTime().getTime());
    }
}
