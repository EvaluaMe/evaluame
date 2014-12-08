package com.garciparedes.resultator;

import java.util.ArrayList;

/**
 * Created by garciparedes on 7/12/14.
 */
public class ListDB {

    private static ArrayList<Subject> masterList = new ArrayList<Subject>();

    public static void setMasterList(ArrayList<Subject> mList) {
        masterList = mList;
    }

    public static ArrayList<Subject> getMasterList() {

        return masterList;
    }

    public static void addSubject(String name, String description){
        masterList.add(new Subject(name, description));

    }

    public static void addTest(int i, String name, float mark, float value){
        masterList.get(i).addTestElement(new Test(name,mark, value));
    }

    public static ArrayList<String> subjectNames(){
        ArrayList<String> names = new ArrayList<String>(masterList.size());

        for (int i = 0; i< masterList.size(); i++){

            names.add(masterList.get(i).getName());

        }

        return names;

    }

    public static ArrayList<Test> getTestList(int i) {
        return masterList.get(i).getTestList();
    }
}
