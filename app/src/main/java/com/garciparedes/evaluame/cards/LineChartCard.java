package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.provider.ListDB;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by garciparedes on 14/2/15.
 */
public class LineChartCard extends BaseChartCard {

    private LineChart mChart;

    /**
     * @param context
     */
    public LineChartCard(Context context) {
        super(context, R.layout.card_chart_line);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        mChart = (LineChart) view.findViewById(R.id.card_chart_line);

        // if enabled, the chart will always start at zero on the y-axis
        //mChart.setStartAtZero(true);

        // disable the drawing of values into the chart
        //mChart.setDrawYValues(false);

        //mChart.setDrawBorder(false);

        //mChart.setDrawLegend(false);

        // no description text
        mChart.setDescription("");
        //mChart.setUnit(" $");

        // enable value highlighting
        mChart.setHighlightEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawGridBackground(false);
        //mChart.setDrawVerticalGrid(false);
        //mChart.setDrawXLabels(false);

        // add data
        setValues();

        mChart.animateXY(2000, 2000);

        // dont forget to refresh the drawing
        mChart.invalidate();
    }

    @Override
    public void setValues() {

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < ListDB.size(); i++) {

            xVals.add(ListDB.get(i).getName());
        }

        ArrayList<Entry> vals1 = new ArrayList<Entry>();

        for (int i = 0; i < ListDB.size(); i++) {
            Entry subject = new Entry((int) ListDB.get(i).getAverage(), i);

            vals1.add(subject);
        }

        // create a dataset and give it a type
        LineDataSet set1 = new LineDataSet(vals1, "DataSet 1");
        set1.setDrawCubic(true);
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(true);
        set1.setDrawCircles(false);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(set1);

        // create a data object with the datasets
        LineData data = new LineData(xVals, dataSets);

        // set data
        mChart.setData(data);
    }
}
