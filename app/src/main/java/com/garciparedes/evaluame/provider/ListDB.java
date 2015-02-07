package com.garciparedes.evaluame.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.items.Test;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * ListDB class
 */
public class ListDB {

    private static ArrayList<Subject> masterList;


    /**
     *
     * @param mList MasterList
     */
    public static void setMasterList(ArrayList<Subject> mList) {
        masterList = mList;
    }


    /**
     *
     * @return masterLsit
     */
    public static ArrayList<Subject> getMasterList() {
        return masterList;
    }


    /**
     *
     * @param context context
     * @param name name
     * @param description description
     */
    public static void addSubject(Context context, String name, String description){
        masterList.add(new Subject(name, description));
        saveData(context);
    }


    /**
     *
     * @param context context
     * @param subjectId subjectid
     */
    public static void removeSubject(Context context, int subjectId){
        masterList.remove(subjectId);
        saveData(context);
    }


    /**
     *
     * @param context context
     * @param subject subject
     */
    public static void removeSubject(Context context, Subject subject){
        masterList.remove(subject);
        saveData(context);
    }


    /**
     *
     * @param context context
     * @param i i
     * @param name name
     * @param mark mark
     * @param value value
     */
    public static void addTest(Context context, int i, String name, float mark, float value){
        masterList.get(i).addTestElement(new Test(name,mark, value));
        saveData(context);
    }




    /**
     *
     * @return ArrayList with names of subjects
     */
    public static ArrayList<String> subjectNames(){
        ArrayList<String> names = new ArrayList<>(masterList.size());

        for (int i = 0; i< masterList.size(); i++){

            names.add(masterList.get(i).getName());

        }

        return names;

    }


    /**
     *
     * @param context context
     */
    public static void saveData(Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context);

        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(ListDB.getMasterList());
        prefsEditor.putString("MasterList", json);
        prefsEditor.apply();
    }

}
