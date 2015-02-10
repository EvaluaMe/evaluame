package com.garciparedes.evaluame.items;

import com.garciparedes.evaluame.Util.Number;

import java.util.GregorianCalendar;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Test {

    private String name;
    private Float mark;
    private Float percentage;

    private GregorianCalendar date;

    public Test(String name, GregorianCalendar date, float mark, float percentage){
        this.name = name;
        this.date = date;
        this.mark = mark;
        this.percentage = percentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public float getMark() {
        return mark;
    }

    public String getMarkString() {
        return Number.toString(getMark());
    }

    public float getPercentage() {
        return percentage;
    }

    public String getPercentageString() {
        return (Number.toString(getPercentage()) + "%");
    }
}
