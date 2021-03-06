package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseChartCard extends Card {

    /**
     * @param context
     * @param innerLayout
     */
    public BaseChartCard(Context context, int innerLayout) {
        super(context, innerLayout);
        init(context);
    }

    /**
     * Init
     */
    private void init(Context context) {
        CustomCardHeader cardHeader = new CustomCardHeader(context);
        cardHeader.setTitle(getContext().getString(R.string.chart));
        addCardHeader(cardHeader);
    }

    public abstract void setValues();

    @Override
    public abstract void setupInnerViewElements(ViewGroup parent, View view);
}
