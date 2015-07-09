package com.garciparedes.evaluame.utils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 * Created by garciparedes on 10/2/15.
 */
public class Number {

    public static final String NAN_STRING = "";


    public static boolean isExact(float f) {
        return ((f % 1) == 0);
    }


    public static String toString(Float f, String symbol) {
        return toString(f) + symbol;
    }

    public static String toString(Float f) {
        String str;

        if (f.isNaN()) {
            str = NAN_STRING;
        } else if (isExact(f)) {
            str = Integer.toString(f.intValue());
        } else {
            str = round(f, 3).toString();
        }

        return str;
    }

    /**
     * Code from Internet (StackOverFlow)
     * <p/>
     * Author: Jav_Rock     http://stackoverflow.com/users/744859/jav-rock
     * <p/>
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

    public static int[] convertIntegers(List<Integer> integers)
    {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }
}
