package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 7/2/15.
 */
public class DetailsCard extends Card {

    private TextView mTitle;
    private TextView mSecondaryTitle;

    private Subject subject;



    public DetailsCard(Context context, Subject subject) {
        super(context, R.layout.card_details);
        init(context);
        this.subject = subject;
    }

    private void init(Context context){

    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.card_details_name_textView);
        mSecondaryTitle = (TextView) parent.findViewById(R.id.card_details_description_textView);

        if (mTitle != null)
            mTitle.setText(subject.getName());

        if (mSecondaryTitle != null)
            mSecondaryTitle.setText(subject.getDescription());


    }

    @Override
    public int getType() {
        //Very important with different inner layouts
        return 1;
    }
}
