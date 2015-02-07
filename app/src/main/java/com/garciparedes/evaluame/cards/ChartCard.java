package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 7/2/15.
 */
public class ChartCard extends Card {


    /**
     * Constructor with a custom inner layout
     * @param context
     */
    public ChartCard(Context context) {
        this(context, R.layout.card_chart);
    }

    /**
     *
     * @param context
     * @param innerLayout
     */
    public ChartCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init();
    }

    /**
     * Init
     */
    private void init(){

        //No Header

        //Set a OnClickListener listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {


    }



}
