package com.garciparedes.evaluame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by garciparedes on 24/12/14.
 */
public class DefaultFragment  extends Fragment {

    private BarChart mChart;
    
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_default, container, false);

        mChart = (BarChart) view.findViewById(R.id.chart_general);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        mChart.setDescription("");
        mChart.setDrawLegend(false);

        setValues();

    }


    public void setValues(){

        ArrayList<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0 ; i < ListDB.getMasterList().size(); i++){
            BarEntry subject = new BarEntry(ListDB.getMasterList().get(i).getAverage(), i);
            valsComp1.add(subject);
            xVals.add(ListDB.getMasterList().get(i).getName());
        }

        BarDataSet setComp1 = new BarDataSet(valsComp1, "Company 1");
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(setComp1);


        BarData data = new BarData(xVals, dataSets);
        mChart.setData(data);

        mChart.animateY(1000);

    }

}
