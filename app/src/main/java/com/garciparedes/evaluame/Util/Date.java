package com.garciparedes.evaluame.Util;

import android.content.Context;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 10/2/15.
 */
public class Date {
    public static String intToStringMonth(Context context, int month){
        return context.getResources().getStringArray(R.array.month)[month];
    }
}
