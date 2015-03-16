package com.garciparedes.evaluame.enums;

import android.content.Context;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 18/2/15.
 */
public enum ExamType {

    DELIVERY(0),
    CONTINUEWORK(1),
    HOMEWORK(2),
    PRACTICE(3),
    EXAM(4);

    private int friendlyName;

    private ExamType(int friendlyName) {
        this.friendlyName = friendlyName;
    }


    public static String[] values(Context context){
        String result[] = new String[values().length];
        for (int i = 0; i < values().length; i++){
            result[i] = values()[i].toString(context);
        }
        return result;
    }
    public String toString(Context context){
        return context.getResources().getStringArray(R.array.exam_type)[friendlyName];
    }
}
