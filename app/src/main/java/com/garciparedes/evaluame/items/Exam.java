package com.garciparedes.evaluame.items;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.garciparedes.evaluame.Util.Date;
import com.garciparedes.evaluame.Util.Number;
import com.garciparedes.evaluame.enums.ExamType;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Exam implements Parcelable{


    private String name;
    private float mark;
    private float percentage;
    private ExamType mType;


    /**
     *
     */
    private GregorianCalendar date;


    /**
     *
     */
    public Exam(){
        super();
        this.mType = ExamType.EXAM;
    }


    /**
     *
     * @param name
     * @param date
     * @param mark
     * @param percentage
     */
    public Exam(String name, GregorianCalendar date, float mark, float percentage, ExamType Type){
        this.name = name;
        this.date = date;
        this.mark = mark;
        this.percentage = percentage;
        this.mType = Type;
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
     * @param date
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }


    public void setType(ExamType type) {
        this.mType = type;
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
    public ExamType getType() {
        return this.mType;
    }

    /**
     *
     * @return
     */
    public String getTypeString(Context context) {
        return this.mType.toString();
    }

    /**
     *
     * @return
     */
    public String getPercentageString() {
        return (Number.toString(getPercentage(), "%"));
    }


    public GregorianCalendar getDate() {
        return date;
    }

    public String getDateString(Context context){
        return Date.dateToString(context, getDate());

    }

    @Override
    public String toString() {
        return super.toString();
    }
    /*
    *<--------------------------------PARCELABLE-------------------------------->
    */


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
        dest.writeFloat(mark);
        dest.writeFloat(percentage);
        dest.writeSerializable(date);
    }

    public static final Parcelable.Creator<Exam> CREATOR
            = new Parcelable.Creator<Exam>() {
        public Exam createFromParcel(Parcel in) {
            return new Exam(in);
        }

        public Exam[] newArray(int size) {
            return new Exam[size];
        }
    };

    private Exam(Parcel in) {
        name = in.readString();
        mark = in.readFloat();
        percentage = in.readFloat();
        date = (GregorianCalendar) in.readSerializable();
    }
}
