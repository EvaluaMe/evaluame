package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 7/2/15.
 */
public class PieChartCard extends Card {


    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public PieChartCard(Context context) {
        this(context, R.layout.card_chart);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public PieChartCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    /**
     * Init
     */
    private void init(){

        //No Header
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {


    }



}
