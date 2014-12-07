package com.garciparedes.resultator;

import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 28/08/14.
 */

public class CustomArrayAdapter extends ArrayAdapter<Test> {

    private final Activity context;
    private final ArrayList<Test> datos;


    CustomArrayAdapter(Fragment context, ArrayList<Test> datos) {
        super(context.getActivity(), R.layout.listitem_test, datos);
        this.context = context.getActivity();
        this.datos = datos;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_test, null);

        TextView lblName = (TextView)item.findViewById(R.id.LblName);
        lblName.setText(datos.get(position).getName());

        TextView lblMark = (TextView)item.findViewById(R.id.LblMark);
        lblMark.setText( datos.get(position).getMarkString());


        return(item);
    }
}

