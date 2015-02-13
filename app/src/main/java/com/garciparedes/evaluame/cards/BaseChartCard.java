package com.garciparedes.evaluame.cards;

import android.content.Context;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseChartCard extends Card {

    /**
     *
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
    private void init(Context context){
        CardHeader cardHeader = new CardHeader(context);
        cardHeader.setTitle(getContext().getString(R.string.title_chart));
        addCardHeader(cardHeader);
    }

    public abstract void setValues();
}
