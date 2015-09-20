package com.garciparedes.evaluame.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;

import java.util.ArrayList;

/**
 * Created by garciparedes on 14/6/15.
 */
public class ListAtrib {

    private Drawable mImage;
    private String mString;

    public static ArrayList<ListAtrib> generate (Context context, Exam exam){
        ArrayList<ListAtrib> arrayList = new ArrayList<>();

        arrayList.add(
                new ListAtrib(
                        context.getResources().getDrawable(R.drawable.ic_action_weight)
                        ,exam.getPercentageString())
        );

        arrayList.add(
                new ListAtrib(
                        context.getResources().getDrawable(R.drawable.ic_action_event)
                        ,exam.getDateString(context))
        );

        arrayList.add(
                new ListAtrib(
                        context.getResources().getDrawable(R.drawable.ic_action_time)
                        ,exam.getTimeString(context))
        );

        arrayList.add(
                new ListAtrib(
                        context.getResources().getDrawable(R.drawable.ic_action_edit_dark)
                        ,exam.getTypeString(context))
        );

        arrayList.add(
                new ListAtrib(
                        context.getResources().getDrawable(R.drawable.ic_action_accept_dark)
                        ,exam.getMarkString())
        );


        return arrayList;
    }


    public ListAtrib (Drawable image, String string){
        this.mImage = image;
        this.mString = string;
    }


    public Drawable getImage() {
        return mImage;
    }

    public String getString() {
        return mString;
    }

    public void setImage(Drawable image) {
        this.mImage = image;
    }

    public void setString(String string) {
        this.mString = string;
    }
}
