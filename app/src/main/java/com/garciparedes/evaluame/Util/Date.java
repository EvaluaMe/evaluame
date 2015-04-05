package com.garciparedes.evaluame.Util;

import android.content.Context;

import com.garciparedes.evaluame.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by garciparedes on 10/2/15.
 */
public class Date {
    public static String intToStringMonth(Context context, int month) {
        return context.getResources().getStringArray(R.array.month)[month];
    }

    public static String dateToString(Context context, GregorianCalendar gregorianCalendar) {
        StringBuilder result = new StringBuilder();
        try {

            result.append(gregorianCalendar.get(Calendar.DAY_OF_MONTH));
            result.append("/");
            result.append(intToStringMonth(context, gregorianCalendar.get(Calendar.MONTH)));
            result.append("/");
            result.append(gregorianCalendar.get(Calendar.YEAR));
        } catch (NullPointerException e) {
            result = nullTimeToString();
        }

        return result.toString();
    }

    public static String timeToString(Context context, GregorianCalendar gregorianCalendar) {
        StringBuilder result = new StringBuilder();
        try {

            result.append(gregorianCalendar.get(Calendar.HOUR_OF_DAY));
            result.append(":");
            result.append(gregorianCalendar.get(Calendar.MINUTE));
        } catch (NullPointerException e) {
            result = nullDateToString();
        }

        return result.toString();
    }

    public static String dateToString(Context context, int year, int month, int day) {
        StringBuilder result = new StringBuilder();
        result.append(day);
        result.append("/");
        result.append(intToStringMonth(context, month));
        result.append("/");
        result.append(year);

        return result.toString();
    }

    public static String upcomingDays(Context context, GregorianCalendar gregorianCalendar){
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

    private static StringBuilder nullDateToString() {
        StringBuilder result = new StringBuilder();
        result.append("--");
        result.append("/");
        result.append("--");
        result.append("/");
        result.append("----");
        return result;
    }



    private static StringBuilder nullTimeToString() {
        StringBuilder result = new StringBuilder();
        result.append("--");
        result.append(":");
        result.append("--");
        return result;
    }
}
