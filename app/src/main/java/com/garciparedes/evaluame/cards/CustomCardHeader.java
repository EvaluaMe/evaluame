package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 27/2/15.
 */
public class CustomCardHeader extends CardHeader {

    public CustomCardHeader(Context context) {
        super(context);

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        parent.setBackgroundColor(getContext().getResources().getColor(R.color.green_app));

    }
}
