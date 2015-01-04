package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by garciparedes on 24/12/14.
 */
public class DefaultFragment  extends Fragment{

    private BarChart mChart;
    private BarData data;
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_default, container, false);

    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        createChart();
        setValues();



    }


    private void createChart() {
        mChart = (BarChart) getView().findViewById(R.id.chart_general);
        mChart.setDescription("");
        mChart.setDrawLegend(false);
    }

    public void setValues(){

        ArrayList<BarEntry> valsComp1 = new ArrayList<>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0 ; i < ListDB.getMasterList().size(); i++){
            BarEntry subject = new BarEntry(ListDB.getMasterList().get(i).getAverage(), i);
            valsComp1.add(subject);
            xVals.add(ListDB.getMasterList().get(i).getName());
        }

        BarDataSet setComp1 = new BarDataSet(valsComp1, "Company 1");
        ArrayList<BarDataSet> dataSets = new ArrayList<>();
        dataSets.add(setComp1);


        BarData data = new BarData(xVals, dataSets);
        mChart.setData(data);

        mChart.animateY(1000);

    }

}
