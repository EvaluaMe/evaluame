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


    /**
     *
     * @param name
     * @param description
     */
    public Subject(String name, String description){
        this.name = name;
        this.description = description;
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
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
    public String getDescription() {
        return description;
    }


    /**
     *
     * @return
     */
    public ArrayList<Test> getTestList() {
        return testList;
    }


    /**
     *
     * @param i
     * @return
     */
    public Test getTestElement(int i) {
        try {
            return getTestList().get(i);
        } catch (NullPointerException e){
            return null;
        }
    }


    /**
     *
     * @param test
     */
    public void addTestElement(Test test){
        getTestList().add(test);
    }


    /**
     *
     * @return
     */
    public float getAverage(){

        float sum = 0;

        for (int i = 0 ; i < getTestList().size() ; i++){
            sum += getTestElement(i).getMark();
        }

        return  sum/getTestList().size();
    }

    /**
     *
     * @return
     */
    public String getAverageString(){
        return Number.toString(getAverage());
    }


    /**
     *
     * @return
     */
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


    /**
     *
     * @return
     */
    public String getRatioString(){
        return Number.toString(getRatio());
    }


    /**
     *
     * @return
     */
    public float getTotalPercentage(){
        float sum = 0;

        for (int i = 0 ; i < getTestList().size() ; i++){
            sum += getTestElement(i).getPercentage();
        }
        return sum;
    }


    public float getWeightedAverage(){
        float dividend = 0;
        float divisor = 0;
        for(int i = 0; i < getTestList().size();i++ ){
            dividend += getTestElement(i).getMark() * getTestElement(i).getPercentage();
            divisor += getTestElement(i).getPercentage();
        }
        return dividend/divisor;
    }

    /**
     *
     * @return
     */
    public String getWeightedAverageString(){
        return Number.toString(getWeightedAverage());
    }

    /**
     *
     * @param test
     */
    public void removeTest(Test test){
        testList.remove(test);
    }
}
