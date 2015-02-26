package com.garciparedes.evaluame.Util;

import com.amulyakhare.textdrawable.util.ColorGenerator;

/**
 * Created by garciparedes on 26/2/15.
 */
public class Color {

    private static ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT


    public static int getRandomColor(){
        return generator.getRandomColor();
    }
}
