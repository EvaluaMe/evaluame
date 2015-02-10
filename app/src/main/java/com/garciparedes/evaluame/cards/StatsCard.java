package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 7/2/15.
 */
public class StatsCard extends Card {

    private TextView averageTitleTextView;
    private TextView averageValueTextView;

    private TextView ratioTitleTextView;
    private TextView ratioValueTextView;

    private Subject subject;


    public StatsCard(Context context, Subject subject) {
        super(context, R.layout.card_stats);
        init(context);
        this.subject = subject;
    }

    private void init(Context context){

        //Create a CardHeader
        CardHeader header = new CardHeader(context);
        header.setTitle(context.getString(R.string.title_stats));
        //header.setButtonExpandVisible(true);

        addCardHeader(header);
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements

        averageTitleTextView = (TextView) parent.findViewById(R.id.card_stats_average_title_textView);
        averageValueTextView = (TextView) parent.findViewById(R.id.card_stats_average_value_textView);

        ratioTitleTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_value_textView);
        ratioValueTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_value_textView);

        if (averageTitleTextView != null)
            averageTitleTextView.setText(
                    getContext().getString(R.string.title_average)
                            + ": "
            );

        if (averageValueTextView != null)
            averageValueTextView.setText(subject.getAverageString());


        if (ratioTitleTextView != null)
            ratioTitleTextView.setText(
                    getContext().getString(R.string.title_ratio)
                            + ": "
            );

        if (ratioValueTextView != null)
            ratioValueTextView.setText(subject.getRatioString());
    }
}
