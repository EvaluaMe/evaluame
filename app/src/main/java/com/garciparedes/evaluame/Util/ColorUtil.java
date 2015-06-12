package com.garciparedes.evaluame.Util;

import android.graphics.Color;

import com.amulyakhare.textdrawable.util.ColorGenerator;

import java.util.ArrayList;

/**
 * Created by garciparedes on 26/2/15.
 */
public class ColorUtil {

    private static final int COLOR_RANGE = 0x101010;
    private static final int COLOR_MAX = 0xFFFFFF;

    private static ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT

    public static int getRandomColor(){
        return generator.getRandomColor();
    }

    public static int getDarkness(int color){
        return (color - COLOR_RANGE);
    }

    public static ArrayList<Integer> getColorPalette(int color){
        ArrayList<Integer> palette = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            palette.add((color - (COLOR_RANGE * i)) % COLOR_MAX);
        }

        return palette;
    }

    /**
     * Returns the complimentary (opposite) color.
     * @param color int RGB color to return the compliment of
     * @return int RGB of compliment color
     */
    public static int getComplimentColor(int color) {
        // get existing colors
        int alpha = Color.alpha(color);
        int red = Color.red(color);
        int blue = Color.blue(color);
        int green = Color.green(color);

        // find compliments
        red = (~red) & 0xff;
        blue = (~blue) & 0xff;
        green = (~green) & 0xff;

        return Color.argb(alpha, red, green, blue);
    }
}
