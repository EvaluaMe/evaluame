package com.garciparedes.evaluame.Util;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by garciparedes on 26/2/15.
 */
public class Color {

    private static final int COLOR_RANGE = 0x121212;
    private static final int COLOR_MAX = 0xFFFFFF;

    private static ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

    public static int getRandomColor(){
        return generator.getRandomColor();
    }

    public static int getDarkness(int color){
        return (color + COLOR_RANGE);
    }

    public static ArrayList<Integer> getColorPalette(int color){
        ArrayList<Integer> palette = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            palette.add((color - (COLOR_RANGE * i)) % COLOR_MAX);
        }

        return palette;
    }
}
