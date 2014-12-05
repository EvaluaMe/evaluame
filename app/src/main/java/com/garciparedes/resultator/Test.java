package com.garciparedes.resultator;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Test {

    private String name;
    private double mark;
    private double value;

    public Test(String name, double mark, double value){
        this.name = name;
        this.mark = mark;
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public double getMark() {
        return mark;
    }

    public double getValue() {
        return value;
    }
}
