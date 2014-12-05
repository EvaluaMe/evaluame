package com.garciparedes.resultator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;


/**
 * Created by garciparedes on 5/12/14.
 */
public class ChartFragment  extends Fragment {

    public static ChartFragment newInstance() {
        ChartFragment f = new ChartFragment();

        LineChart chart = (LineChart) findViewById(R.id.chart);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_chart, container, false);

    }
}