package com.garciparedes.resultator;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Test {

    private String name;
    private float mark;
    private float value;

    public Test(String name, float mark, float value){
        this.name = name;
        this.mark = mark;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public void setValue(float value) {
        this.value = value;
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


    public float getValue() {
        return value;
    }
}
