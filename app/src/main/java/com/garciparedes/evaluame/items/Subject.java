package com.garciparedes.evaluame.items;

import java.util.ArrayList;
import com.garciparedes.evaluame.Util.Number;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Subject {


    private String name;
    private String description;
    private ArrayList<Exam> examList;


    /**
     *
     */
    public Subject(){
        this.examList = new ArrayList<>();
    }


    /**
     *
     * @param name
     * @param description
     */
    public Subject(String name, String description){
        this.name = name;
        this.description = description;
        this.examList = new ArrayList<>();
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
    public ArrayList<Exam> getExamList() {
        return examList;
    }


    /**
     *
     * @param i
     * @return
     */
    public Exam getTestElement(int i) {
        try {
            return getExamList().get(i);
        } catch (NullPointerException e){
            return null;
        }
    }


    /**
     *
     * @param exam
     */
    public void addTestElement(Exam exam){
        getExamList().add(exam);
    }


    /**
     *
     * @return
     */
    public float getAverage(){

        float sum = 0;

        for (int i = 0 ; i < getExamList().size() ; i++){
            sum += getTestElement(i).getMark();
        }

        return  sum/ getExamList().size();
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

        if (getExamList().size() > 0 ){

            for (int i = 0 ; i < getExamList().size() ; i++){
                if (getExamList().get(i).getMark() >= 5) {
                    pass++;
                }
            }

            return  ((float) pass/ getExamList().size());

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

        for (int i = 0 ; i < getExamList().size() ; i++){
            sum += getTestElement(i).getPercentage();
        }
        return sum;
    }


    public float getWeightedAverage(){
        float dividend = 0;
        float divisor = 0;
        for(int i = 0; i < getExamList().size();i++ ){
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
     * @param exam
     */
    public void removeTest(Exam exam){
        examList.remove(exam);
    }
}
