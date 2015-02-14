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

        //if (mChart != null) {
            mChart.setDescription("");
            mChart.setDrawLegend(false);

            // if enabled, the chart will always start at zero on the y-axis
            mChart.setStartAtZero(true);

            // disable the drawing of values into the chart
            mChart.setDrawYValues(false);

            mChart.setDrawBorder(false);

            mChart.setDrawLegend(false);


            // enable value highlighting
            mChart.setHighlightEnabled(true);

            // disable touch gestures
            mChart.setTouchEnabled(false);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);

            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(false);

            mChart.setDrawGridBackground(false);
            mChart.setDrawVerticalGrid(false);
            setValues();

            mChart.animateXY(2000, 2000);

            // dont forget to refresh the drawing
            mChart.invalidate();
        //}
    }

    @Override
    public void setValues() {
        ArrayList<String> xVals = new ArrayList<String>();
        ArrayList<Entry> yVals = new ArrayList<>();



        for (int i = 0 ; i < ListDB.size(); i++){
            Entry subject = new Entry(ListDB.get(i).getAverage(), i);
            yVals.add(subject);
            xVals.add(ListDB.get(i).getName());
        }

        LineDataSet lineDataSet = new LineDataSet(yVals,"");


        lineDataSet.setDrawCubic(true);
        lineDataSet.setCubicIntensity(0.2f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setDrawCircles(false);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleSize(5f);
        lineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        lineDataSet.setColor(Color.rgb(104, 241, 175));

        ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(xVals, dataSets);
        mChart.setData(data);
    }
}
