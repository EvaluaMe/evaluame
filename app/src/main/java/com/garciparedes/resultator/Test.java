package com.garciparedes.resultator;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Test {

    private String name;
    private float mark;
    private float percentage;

    public Test(String name, float mark, float percentage){
        this.name = name;
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
        return Float.toString(mark);
    }


    public float getPercentage() {
        return percentage;
    }
}
