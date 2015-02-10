package com.garciparedes.evaluame.items;

import java.util.ArrayList;
import com.garciparedes.evaluame.Util.Number;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Subject {

    private String name;
    private String description;
    private ArrayList<Test> testList = new ArrayList<Test>();


    public Subject(String name, String description){
        this.name = name;
        this.description = description;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public ArrayList<Test> getTestList() {
        return testList;
    }


    public Test getTestElement(int i) {
        try {
            return getTestList().get(i);
        } catch (NullPointerException e){
            return null;
        }
    }

    public void addTestElement(Test test){
        getTestList().add(test);
    }


    public float getAverage(){

        float sum = 0;

        for (int i = 0 ; i < getTestList().size() ; i++){
            sum += getTestElement(i).getMark();
        }

        return  sum/getTestList().size();
    }

    public String getAverageString(){
        return Number.toString(getAverage());
    }

    public float getRatio(){
        int pass = 0;

        if (getTestList().size() > 0 ){

            for (int i = 0 ; i < getTestList().size() ; i++){
                if (getTestList().get(i).getMark() >= 5) {
                    pass++;
                }
            }

            return  ((float) pass/getTestList().size());

        } else {

            return 0;
        }

    }

    public String getRatioString(){
        return Number.toString(getRatio());
    }


    public float getTotalPercentage(){
        float sum = 0;

        for (int i = 0 ; i < getTestList().size() ; i++){
            sum += getTestElement(i).getPercentage();
        }
        return sum;
    }

    public void removeTest(Test test){
        testList.remove(test);
    }
}
