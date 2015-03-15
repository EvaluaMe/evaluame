package com.garciparedes.evaluame.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.garciparedes.evaluame.items.Mark;
import com.garciparedes.evaluame.items.Subject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

/**
 * ListDB class
 */
public class ListDB {

    private static ArrayList<Subject> masterList;


    /**
     * @param mList MasterList
     */
    public static void setMasterList(ArrayList<Subject> mList) {
        masterList = mList;
    }

    public static ArrayList<Subject> getStarredSubjects(){
        ArrayList<Subject> starreds = new ArrayList<>();
        for(int i = 0 ; i < size(); i++){
            if (get(i).isStarred()){
                starreds.add(get(i));
            }
        }
        return starreds;
    }

    /**
     * @return masterLsit
     */
    public static ArrayList<Subject> getMasterList() {
        return masterList;
    }


    public static Subject get(int i) {
        return getMasterList().get(i);
    }

    public static int size() {
        return getMasterList().size();
    }

    /**
     * @param context     context
     * @param name        name
     * @param description description
     */
    public static void addSubject(Context context, String name, String description) {
        masterList.add(new Subject(name, description));
        saveData(context);
    }

    /**
     * @param context context
     * @param subject newSubject
     */
    public static void addSubject(Context context, Subject subject) {
        if (subject.getName().length() <= 0)
            throw new IllegalArgumentException("Introduce el nombre");

        masterList.add(subject);
        saveData(context);
    }


    /**
     * @param context context
     * @param subject newSubject
     */
    public static void removeSubject(Context context, Subject subject) {
        masterList.remove(subject);
        saveData(context);
    }

    /**
     * @param context context
     * @param subject mSubject
     * @param mark    mark
     */
    public static void addTest(Context context, Subject subject, Mark mark) {

        if(mark.getName().length() <= 0)
            throw new IllegalArgumentException("Introduce el nombre");

        if(mark.getPercentage() <= 0)
            throw new IllegalArgumentException("Introduce el porcentaje");

        if((subject.getTotalPercentage()+ mark.getPercentage()) > 100)
            throw new IllegalArgumentException("El porcentaje no puede superar el 100%");

        subject.addTestElement(mark);
        sortExams(subject);
        saveData(context);
    }

    public static void removeTest(Context context, Subject subject, Mark mark){
        subject.removeTest(mark);
        sortExams(subject);
        ListDB.saveData(context);
    }

    /**
     * @return ArrayList with names of subjects
     */
    public static ArrayList<String> subjectNames() {
        ArrayList<String> names = new ArrayList<>(masterList.size());

        for (int i = 0; i < masterList.size(); i++) {

            names.add(masterList.get(i).getName());

        }

        return names;

    }

    public static void sortExams(Subject subject){
        Collections.sort(subject.getMarkList());
        Collections.reverse(subject.getMarkList());
    }


    /**
     * @param context context
     */
    public static void saveData(Context context) {
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);

        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ListDB.getMasterList());
        prefsEditor.putString("MasterList", json);
        prefsEditor.apply();
    }

}
