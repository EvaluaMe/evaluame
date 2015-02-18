package com.garciparedes.evaluame.enums;

/**
 * Created by garciparedes on 18/2/15.
 */
public enum ExamType {

    PRACTICE("Practica"),
    EXAM("Examen");

    private String friendlyName;

    private ExamType(String friendlyName) {
        this.friendlyName = friendlyName;
    }


    @Override
    public String toString() {
        return friendlyName;
    }
}
