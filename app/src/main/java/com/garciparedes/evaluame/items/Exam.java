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
public class Exam implements Parcelable, Comparable<Exam> {


    private String name;
    private float mark;
    private float percentage;
    private ExamType mType;
    private GregorianCalendar date;


    /**
     *
     */
    public Exam() {
        super();
        this.mType = ExamType.EXAM;
    }


    /**
     * @param name
     * @param date
     * @param mark
     * @param percentage
     */
    public Exam(String name, GregorianCalendar date, float mark, float percentage, ExamType type) {
        this.name = name;
        this.date = date;
        this.mark = mark;
        this.percentage = percentage;
        this.mType = type;
    }

    public Exam copy(){
        return new Exam(getName(), getDate(),getMark(),getPercentage(),getType());
    }


    public void paste(Exam exam){
        setName(exam.getName());
        setDate(exam.getDate());
        setMark(exam.getMark());
        setPercentage(exam.getPercentage());
        setType(exam.getType());
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param mark
     */
    public void setMark(float mark) {
        this.mark = mark;
    }


    /**
     * @param percentage
     */
    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }


    /**
     * @param date
     */
    public void setDate(GregorianCalendar date) {
        this.date = date;
    }


    public void setType(ExamType type) {
        this.mType = type;
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
    public float getMark() {
        return mark;
    }


    /**
     * @return
     */
    public String getMarkString() {

        try {
            if (getDate().after(Calendar.getInstance()))
                return Number.NAN_STRING;

        } catch (NullPointerException ignored){};

        return Number.toString(getMark());
    }

    /**
     * @return
     */
    public float getPercentage() {
        return percentage;
    }


    /**
     * @return
     */
    public ExamType getType() {
        return this.mType;
    }

    /**
     * @return
     */
    public String getTypeString(Context context) {
        return mType.toString(context);
    }

    /**
     * @return
     */
    public String getPercentageString() {

        if (getPercentage() == 0){
            return Number.NAN_STRING + "%";
        }

        return (Number.toString(getPercentage(), "%"));
    }


    public GregorianCalendar getDate() {
        return date;
    }

    public String getDateString(Context context) {
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
        dest.writeInt(mType.ordinal());
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
        mType = ExamType.values()[in.readInt()];
    }

    /**
     * Compares this object to the specified object to determine their relative
     * order.
     *
     * @param another the object to compare to this instance.
     * @return a negative integer if this instance is less than {@code another};
     * a positive integer if this instance is greater than
     * {@code another}; 0 if this instance has the same order as
     * {@code another}.
     * @throws ClassCastException if {@code another} cannot be converted into something
     *                            comparable to {@code this} instance.
     */
    @Override
    public int compareTo(Exam another) {
        if (getDate() == null){
            return -1;
        } else if (another.getDate() == null){
            return 1;
        } else {
            return getDate().compareTo(another.getDate());
        }
    }
}
