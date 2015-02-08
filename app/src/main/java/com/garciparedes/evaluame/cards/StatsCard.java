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
public class StatsCard extends Card {

    private TextView averageTextView;
    private TextView ratioTextView;

    private Subject subject;


    public StatsCard(Context context, Subject subject) {
        super(context, R.layout.card_stats);
        init(context);
        this.subject = subject;
    }

    private void init(Context context){

    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        averageTextView = (TextView) parent.findViewById(R.id.card_stats_average_textView);
        ratioTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_textView);

        if (averageTextView != null)
            averageTextView.setText(""+subject.getAverage());

        if (ratioTextView != null)
            ratioTextView.setText(""+subject.getRatio());


    }
}
