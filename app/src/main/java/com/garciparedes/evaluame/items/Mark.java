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
public class Mark implements Parcelable, Comparable<Mark> {


    private String name;
    private float value;
    private float percentage;
    private ExamType mType;


    /**
     *
     */
    private GregorianCalendar date;


    /**
     *
     */
    public Mark() {
        super();
        this.mType = ExamType.EXAM;
    }


    /**
     * @param name
     * @param date
     * @param value
     * @param percentage
     */
    public Mark(String name, GregorianCalendar date, float value, float percentage, ExamType type) {
        this.name = name;
        this.date = date;
        this.value = value;
        this.percentage = percentage;
        this.mType = type;
    }

    public Mark copy(){
        return new Mark(getName(), getDate(), getValue(),getPercentage(),getType());
    }


    public void paste(Mark mark){

        if(mark.getName().length() <= 0)
            throw new IllegalArgumentException("Introduce el nombre");

        if(mark.getPercentage() <= 0)
            throw new IllegalArgumentException("Introduce el porcentaje");

        setName(mark.getName());
        setDate(mark.getDate());
        setValue(mark.getValue());
        setPercentage(mark.getPercentage());
        setType(mark.getType());
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @param value
     */
    public void setValue(float value) {
        this.value = value;
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
    public float getValue() {
        return value;
    }


    /**
     * @return
     */
    public String getMarkString() {

        try {
            if (getDate().after(Calendar.getInstance()))
                return "--";
        } catch (NullPointerException ignored){};

        return Number.toString(getValue());
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
        dest.writeFloat(value);
        dest.writeFloat(percentage);
        dest.writeSerializable(date);
        dest.writeInt(mType.ordinal());
    }

    public static final Parcelable.Creator<Mark> CREATOR
            = new Parcelable.Creator<Mark>() {
        public Mark createFromParcel(Parcel in) {
            return new Mark(in);
        }

        public Mark[] newArray(int size) {
            return new Mark[size];
        }
    };

    private Mark(Parcel in) {
        name = in.readString();
        value = in.readFloat();
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
    public int compareTo(Mark another) {
        if (getDate() == null){
            return -1;
        } else if (another.getDate() == null){
            return 1;
        } else {
            return getDate().compareTo(another.getDate());
        }
    }
}
