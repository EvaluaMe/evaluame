package com.garciparedes.evaluame.items;

import android.os.Parcel;
import android.os.Parcelable;

import com.garciparedes.evaluame.Util.Color;
import com.garciparedes.evaluame.Util.Number;

import java.util.ArrayList;

/**
 * Created by garciparedes on 5/12/14.
 */
public class Subject implements Parcelable {


    private String name;
    private String description;
    private ArrayList<Mark> mMarkList;


    private boolean mStarred;
    private int mColor;
    /**
     *
     */
    public Subject() {
        this.mMarkList = new ArrayList<>();
    }

    /**
     * @param name
     * @param description
     */
    public Subject(String name, String description) {
        this.name = name;
        this.description = description;
        this.mStarred = false;
        this.mMarkList = new ArrayList<>();
        this.mColor = Color.getRandomColor();
    }

    /**
     * @param name
     * @param description
     */
    public Subject(String name, String description, ArrayList<Mark> markList, int color) {
        this.name = name;
        this.description = description;
        this.mStarred = false;
        this.mMarkList = markList;
        this.mColor = color;
    }


    public Subject copy(){
        return new Subject(getName(), getDescription(), getMarkList(), getColor());

    }

    public void paste(Subject subject){
        if (subject.getName().length() <= 0)
            throw new IllegalArgumentException("Introduzca el nombre");

        setName(subject.getName());
        setDescription(subject.getDescription());
        setMarkList(subject.getMarkList());
        setColor(subject.getColor());

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


    public void setMarkList(ArrayList<Mark> mMarkList) {
        this.mMarkList = mMarkList;
    }

    public void setStarred(boolean Starred) {
        this.mStarred = Starred;
    }

    public void setColor(int Color) {
        this.mColor = mColor;
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


    public boolean isStarred() {
        return mStarred;
    }

    /**
     * @return
     */
    public ArrayList<Mark> getMarkList() {
        return mMarkList;
    }

    public int getColor() {
        if(mColor == 0){
            mColor = Color.getRandomColor();
        }
        return mColor;
    }

    public String getDropCap(){
        return getName().substring(0,1);
    }

    /**
     * @param i
     * @return
     */
    public Mark getTestElement(int i) {
        try {
            return getMarkList().get(i);
        } catch (NullPointerException e) {
            return null;
        }
    }


    /**
     * @param mark
     */
    public void addTestElement(Mark mark) {
        getMarkList().add(mark);
    }


    /**
     * @return
     */
    public float getAverage() {

        float sum = 0;

        for (int i = 0; i < getMarkList().size(); i++) {
            sum += getTestElement(i).getValue();
        }

        Float f = sum / getMarkList().size();
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

        if (getMarkList().size() > 0) {

            for (int i = 0; i < getMarkList().size(); i++) {
                if (getMarkList().get(i).getValue() >= 5) {
                    pass++;
                }
            }

            return ((float) pass / getMarkList().size());

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

        for (int i = 0; i < getMarkList().size(); i++) {
            sum += getTestElement(i).getPercentage();
        }
        return sum;
    }


    public float getWeightedAverage() {
        float dividend = 0;
        for (int i = 0; i < getMarkList().size(); i++) {
            dividend += getTestElement(i).getValue() * getTestElement(i).getPercentage();
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
     * @param mark
     */
    public void removeTest(Mark mark) {
        mMarkList.remove(mark);
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
        dest.writeByte((byte) (mStarred ? 1 : 0));
        dest.writeList(mMarkList);
        dest.writeInt(mColor);
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
        mStarred = in.readByte() != 0;
        mMarkList = in.readArrayList(Mark.class.getClassLoader());
        mColor = in.readInt();
    }
}
