package com.garciparedes.evaluame.items;

import com.garciparedes.evaluame.Util.Number;

import java.util.GregorianCalendar;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Exam {


    private String name;
    private Float mark;
    private Float percentage;


    /**
     *
     */
    private GregorianCalendar date;


    /**
     *
     */
    public Exam(){
        super();
    }


    /**
     *
     * @param name
     * @param date
     * @param mark
     * @param percentage
     */
    public Exam(String name, GregorianCalendar date, float mark, float percentage){
        this.name = name;
        this.date = date;
        this.mark = mark;
        this.percentage = percentage;
    }


    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *
     * @param mark
     */
    public void setMark(float mark) {
        this.mark = mark;
    }


    /**
     *
     * @param percentage
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }


    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     *
     * @return
     */
    public float getMark() {
        return mark;
    }


    /**
     *
     * @return
     */
    public String getMarkString() {
        return Number.toString(getMark());
    }

    /**
     *
     * @return
     */
    public float getPercentage() {
        return percentage;
    }


    /**
     *
     * @return
     */
    public String getPercentageString() {
        return (Number.toString(getPercentage()) + "%");
    }
}
