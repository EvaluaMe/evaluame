package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 7/2/15.
 */
public class PieChartCard extends BaseChartCard {


    private PieChart mChart;
    private PieDataSet yVals;
    private PieData data;
    private ArrayList<String> xVals;
    
    private Subject subject;


    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public PieChartCard(Context context, Subject subject) {
        this(context, R.layout.card_chart_pie);
        this.subject = subject;
    }


    /**
     *
     * @param context
     * @param innerLayout
     */
    private PieChartCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init(context);

    }


    /**
     * Init
     */
    private void init(Context context){
        CardHeader cardHeader = new CardHeader(context);
        cardHeader.setTitle(getContext().getString(R.string.title_chart));
        addCardHeader(cardHeader);
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        mChart = (PieChart) parent.findViewById(R.id.chart);
        mChart.setTouchEnabled(false);
        mChart.setDrawLegend(false);
        mChart.setCenterTextSize(22f);

        mChart.setDescription("");

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        setValues();
    }

    @Override
    public void setValues() {
        xVals = new ArrayList<String>();
        yVals = new PieDataSet(null, "Company 1");

        //int[] rainbow = getResources().getIntArray(R.array.rainbow);
        //yVals.setColors(rainbow);

        data = new PieData(xVals,yVals);

        mChart.setData(data);

        for (int j = 0 ; j< subject.getExamList().size() ; j++){
            introduce(subject.getExamList().get(j),j);
        }

        mChart.setData(data);
        mChart.animateXY(1500, 1500);
    }

    private void introduce(Exam exam, int i){

        xVals.add(exam.getName());
        float f = exam.getMark()* exam.getPercentage();
        float t = (10- exam.getMark())* exam.getPercentage();

        data.addEntry(new Entry(f,i), 0);
        data.addEntry(new Entry(t,i),1);

    }
}
