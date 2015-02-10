package com.garciparedes.evaluame.Util;

/**
 * Created by garciparedes on 10/2/15.
 */
public class Number {

    public static boolean isExact(float f){
        return((f % 1)== 0);
    }

    public static String toString (float f){
        String str;

        if (isExact(f)){
            str = Integer.toString((int) f);
        } else {
            str = Float.toString(f);
        }

        return str;
    }
}
