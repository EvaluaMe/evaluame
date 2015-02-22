package com.garciparedes.evaluame.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.garciparedes.evaluame.Util.Number;

import java.util.ArrayList;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Subject implements Parcelable {


    private String name;
    private String description;
    private ArrayList<Exam> examList;


    /**
     *
     */
    public Subject() {
        this.examList = new ArrayList<>();
    }


    /**
     * @param name
     * @param description
     */
    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
        this.examList = new ArrayList<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Subject copy(){
        try {
            return (Subject) clone();
        }catch (CloneNotSupportedException e){
            return new Subject();
        }
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return
     */
    public String getName() {
        return name;
    }


    /**
     * @return
     */
    public String getDescription() {
        return description;
    }


    /**
     * @return
     */
    public ArrayList<Exam> getExamList() {
        return examList;
    }


    /**
     * @param i
     * @return
     */
    public Exam getTestElement(int i) {
        try {
            return getExamList().get(i);
        } catch (NullPointerException e) {
            return null;
        }
    }


    /**
     * @param exam
     */
    public void addTestElement(Exam exam) {
        getExamList().add(exam);
    }


    /**
     * @return
     */
    public float getAverage() {

        float sum = 0;

        for (int i = 0; i < getExamList().size(); i++) {
            sum += getTestElement(i).getMark();
        }

        Float f = sum / getExamList().size();
        if (f.isNaN()) {
            f = (float) 0;
        }
        return f;
    }

    /**
     * @return
     */
    public String getAverageString() {
        return Number.toString(getAverage());
    }


    /**
     * @return
     */
    public float getRatio() {
        int pass = 0;

        if (getExamList().size() > 0) {

            for (int i = 0; i < getExamList().size(); i++) {
                if (getExamList().get(i).getMark() >= 5) {
                    pass++;
                }
            }

            return ((float) pass / getExamList().size());

        } else {

            return 0;
        }

    }


    /**
     * @return
     */
    public String getRatioString() {
        return Number.toString(getRatio());
    }


    /**
     * @return
     */
    public float getTotalPercentage() {
        float sum = 0;

        for (int i = 0; i < getExamList().size(); i++) {
            sum += getTestElement(i).getPercentage();
        }
        return sum;
    }


    public float getWeightedAverage() {
        float dividend = 0;
        for (int i = 0; i < getExamList().size(); i++) {
            dividend += getTestElement(i).getMark() * getTestElement(i).getPercentage();
        }
        return dividend / 100;
    }

    /**
     * @return
     */
    public String getWeightedAverageString() {
        return Number.toString(getWeightedAverage());
    }

    /**
     * @param exam
     */
    public void removeTest(Exam exam) {
        examList.remove(exam);
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable's
     * marshalled representation.
     *
     * @return a bitmask indicating the set of special object types marshalled
     * by the Parcelable.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeTypedList(examList);
    }

    public static final Parcelable.Creator<Subject> CREATOR
            = new Parcelable.Creator<Subject>() {
        public Subject createFromParcel(Parcel in) {
            return new Subject(in);
        }

        public Subject[] newArray(int size) {
            return new Subject[size];
        }
    };

    private Subject(Parcel in) {
        name = in.readString();
        description = in.readString();
        in.readTypedList(examList, Exam.CREATOR);
    }
}
