package com.garciparedes.evaluame.Util;

import java.math.BigDecimal;

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
            str = round(f, 3).toString();
        }

        return str;
    }

    /**
     * Code from Internet (StackOverFlow)
     *
     * Author: Jav_Rock     http://stackoverflow.com/users/744859/jav-rock
     *
     * URL: http://stackoverflow.com/a/8911683/3921457
     *
     * @param d
     * @param decimalPlace
     * @return
     */
    public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        bd = bd.stripTrailingZeros();
        return bd;
    }
}
