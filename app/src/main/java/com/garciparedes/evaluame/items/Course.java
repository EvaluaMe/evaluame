package com.garciparedes.evaluame.items;

import com.garciparedes.evaluame.Util.*;

import java.util.ArrayList;

/**
 * Created by garciparedes on 21/2/15.
 */
public class Course {

    private String mName;
    private String mDescription;

    private ArrayList<Subject> mSubjects;


    public Course(){
    }

    public Course(String name, String description){
        this.mName = name;
        this.mDescription = description;
        this.mSubjects = new ArrayList<>();
    }
}
