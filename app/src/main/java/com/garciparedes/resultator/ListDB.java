package com.garciparedes.resultator;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by garciparedes on 7/12/14.
 */


public class ListDB {

    private static ArrayList<Subject> masterList = inicializa();

    public static void setMasterList(ArrayList<Subject> masterList) {
        masterList = masterList;
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

    private static ArrayList<Subject> inicializa(){
        masterList = new ArrayList<Subject>();

        Subject mate = new Subject("mate", "mierda");
            mate.addTestElement(new Test("tema1",6,3));
            mate.addTestElement(new Test("tema2",6,3));
            mate.addTestElement(new Test("Practica 1",8,3));
            mate.addTestElement(new Test("Practica 2",9,3));
        masterList.add(mate);

        Subject ingles = new Subject("ingles", "mierda");
            ingles.addTestElement(new Test("tema1",6,20));
            ingles.addTestElement(new Test("tema2",4,30));
            ingles.addTestElement(new Test("tema3",3,40));
            ingles.addTestElement(new Test("tema4",6,20));
        masterList.add(ingles);

        Subject pOO = new Subject("Programacion orientada a Objectos", "mierda");
            pOO.addTestElement(new Test("tema1",3,3));
            pOO.addTestElement(new Test("tema2",9,3));
            pOO.addTestElement(new Test("tema3",6,3));
            pOO.addTestElement(new Test("tema4",7,3));
        masterList.add(pOO);

        Subject fisica = new Subject("fisica", "mierda");
            fisica.addTestElement(new Test("tema1",6,3));
            fisica.addTestElement(new Test("examen final",5,30));
            fisica.addTestElement(new Test("tema3",6,3));
            fisica.addTestElement(new Test("tema4",6,3));
        masterList.add(fisica);

        return masterList;
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
