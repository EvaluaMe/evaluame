package com.garciparedes.evaluame.utils;

import android.content.Context;
import android.text.format.DateFormat;

import com.garciparedes.evaluame.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Utility class to Date.
 *
 * Created by garciparedes on 10/2/15.
 */
public class Date {


    /**
     * Static function named dateToString
     * function returns date in String type
     * based on internal date format.
     *
     * @param context App Context
     * @param calendar Calendar
     * @return date in String format.
     */
    public static String dateToString(Context context, Calendar calendar) {
        return DateFormat.getLongDateFormat(context).format(calendar.getTime());
    }


    /**
     * Static function named timeToString
     * that returns date in String type
     * based on internal date format.
     *
     * @param context App Context
     * @param calendar Calendar
     * @return date in String format.
     */
    public static String timeToString(Context context, Calendar calendar) {
        return DateFormat.getTimeFormat(context).format(calendar.getTime());
    }


    /**
     * Static function named remainingTime
     * that returns a String expresing
     * remaining time to now.
     *
     * @param context App Context
     * @param gregorianCalendar Calendar
     * @return remaining time
     */
    public static String remainingTime(Context context, GregorianCalendar gregorianCalendar){
        StringBuilder result = new StringBuilder();
        long millis = (gregorianCalendar.getTimeInMillis() - Calendar.getInstance().getTimeInMillis());
        int days = (int) (millis /(1000*60*60*24));
        int hours = (int) (millis /(1000*60*60));
        int minutes = (int) (millis /(1000*60));

        if (days > 0) {
            result.append(days);
            result.append(" ");
            result.append(context.getResources().getString(R.string.days_left));

        } else if (hours > 0){
            result.append(hours);
            result.append(" ");
            result.append(context.getResources().getString(R.string.hours_left));

        } else if (minutes > 0){
            result.append(minutes);
            result.append(" ");
            result.append(context.getResources().getString(R.string.minutes_left));

        }
        return result.toString();
    }
}
