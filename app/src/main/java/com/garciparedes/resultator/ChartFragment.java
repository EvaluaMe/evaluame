package com.garciparedes.resultator;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
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
        mChart.setCenterText("Quarterly\nRevenue");
        mChart.setCenterTextSize(22f);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);


        insertData();

        mChart.setTouchEnabled(true);

        mChart.animateXY(500,500);


    }


    private void insertData(){
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();

        valsComp1.add(new Entry(50.000f, 0));
        valsComp1.add(new Entry(50.000f, 1));
        valsComp1.add(new Entry(50.000f, 2));
        valsComp1.add(new Entry(50.000f, 3));
        valsComp1.add(new Entry(50.000f, 4));

        // and so on ...

        PieDataSet yVals = new PieDataSet(valsComp1, "Company 1");

        int[] rainbow = getResources().getIntArray(R.array.rainbow);

        yVals.setColors(rainbow);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q"); xVals.add("5.Q");

        PieData data = new PieData(xVals,yVals);

        mChart.setData(data);


    }
}