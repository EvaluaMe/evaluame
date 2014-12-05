package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;


/**
 * Created by garciparedes on 5/12/14.
 */
public class ChartFragment  extends Fragment {

    public static ChartFragment newInstance() {
        ChartFragment f = new ChartFragment();
        return f;
    }

    private PieChart mChart;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chart, container, false);

    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        mChart = (PieChart) getView().findViewById(R.id.chart);

        mChart.setDescription("");


        mChart.setUsePercentValues(true);
        mChart.setCenterText("FOE");
        mChart.setCenterTextSize(22f);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);
        

        mChart.animateXY(2000,2000);

        createData();



    }


    private void createData(){


        int i=0;
        PieDataSet yVals = new PieDataSet(null, "Company 1");

        int[] rainbow = getResources().getIntArray(R.array.rainbow);
        yVals.setColors(rainbow);

        ArrayList<String> xVals = new ArrayList<String>();


        PieData data = new PieData(xVals,yVals);

        mChart.setData(data);


        xVals.add("Tema 1");

        data.addEntry(new Entry(50.000f, i++), 0);

        xVals.add("Tema 2");

        data.addEntry(new Entry(50.000f, i++), 0);

        xVals.add("Tema 3");

        data.addEntry(new Entry(50.000f, i++), 0);

    }




}