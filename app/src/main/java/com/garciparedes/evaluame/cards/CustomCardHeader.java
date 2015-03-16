package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 27/2/15.
 */
public class CustomCardHeader extends CardHeader {

    private int mColor;
    public CustomCardHeader(Context context, int color) {
        super(context, R.layout.card_header_custom);
        this.mColor = color;
    }

    public CustomCardHeader(Context context) {
        super(context, R.layout.card_header_custom);
        this.mColor = context.getResources().getColor(R.color.green_app);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {
        super.setupInnerViewElements(parent, view);
        parent.setBackgroundColor(mColor);
        //Add simple title to header
        if (view!=null){
            TextView mTitleView=(TextView) view.findViewById(R.id.card_header_inner_simple_title);
            if (mTitleView!=null)
                mTitleView.setText(mTitle);
                mTitleView.setTextColor(getContext().getResources().getColor(R.color.white));
        }
    }
}
