package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.provider.ListDB;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 8/2/15.
 */
public class BarChartCard extends BaseChartCard {

    private BarChart barChart;


    public BarChartCard(Context context){
        super(context, R.layout.card_chart_bar);
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        barChart = (BarChart) parent.findViewById(R.id.card_chart_bar);

        if (barChart != null) {
            barChart.setDescription("");
            barChart.setDrawLegend(false);

            setValues();
        }
    }

    /**
     *
     */
    @Override
    public void setValues(){

        ArrayList<BarEntry> valsComp1 = new ArrayList<BarEntry>();
        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0 ; i < ListDB.size(); i++){
            BarEntry subject = new BarEntry(ListDB.get(i).getAverage(), i);
            valsComp1.add(subject);
            xVals.add(ListDB.get(i).getName());
        }

        BarDataSet setComp1 = new BarDataSet(valsComp1, "Company 1");
        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(setComp1);


        BarData data = new BarData(xVals, dataSets);
        barChart.setData(data);

        barChart.animateY(1000);

    }
}
