package com.garciparedes.resultator;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by garciparedes on 28/08/14.
 */

public class TestListAdapter extends ArrayAdapter<Test> {

    private final Activity context;
    private final ArrayList<Test> datos;
    private SparseBooleanArray mSelectedItemsIds;


    TestListAdapter(Fragment context, ArrayList<Test> datos) {
        super(context.getActivity(), R.layout.listitem_test, datos);
        this.context = context.getActivity();
        this.datos = datos;
        mSelectedItemsIds = new SparseBooleanArray();


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.listitem_test, null);

        TextView lblName = (TextView)item.findViewById(R.id.LblName);
        lblName.setText(datos.get(position).getName());

        TextView lblMark = (TextView)item.findViewById(R.id.LblMark);
        lblMark.setText( datos.get(position).getMarkString());

        TextView lblValue = (TextView)item.findViewById(R.id.LblValue);
        lblValue.setText( datos.get(position).getPercentageString());


        return(item);
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    @Override
    public void remove(Test object) {
        datos.remove(object);
        notifyDataSetChanged();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}

