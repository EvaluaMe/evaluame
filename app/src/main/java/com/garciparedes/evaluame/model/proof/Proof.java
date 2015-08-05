package com.garciparedes.evaluame.model.proof;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by garciparedes on 1/8/15.
 */
@ParseClassName(Proof.CLASS_NAME)
public abstract class Proof extends ParseObject{


    public static final String CLASS_NAME = "Proof";

    private static final String NAME_TYPE = "name";



    public void setName(String name){
        put(NAME_TYPE, name);
    }



    public String getName(){
        return getString(NAME_TYPE);
    }



    public abstract double getMax();
}
