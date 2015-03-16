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
public class StatsSubjectCard extends Card {

    private TextView weightedAverageTitleTextView;
    private TextView weightedAverageValueTextView;

    private TextView averageTitleTextView;
    private TextView averageValueTextView;

    private TextView ratioTitleTextView;
    private TextView ratioValueTextView;

    private Subject subject;


    public StatsSubjectCard(Context context, Subject subject) {
        super(context, R.layout.card_stats);
        this.subject = subject;
        init(context);
    }

    private void init(Context context) {
        //Create a CardHeader
        CustomCardHeader header = new CustomCardHeader(context, subject.getColor());
        header.setTitle(context.getString(R.string.title_stats));
        addCardHeader(header);
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        weightedAverageTitleTextView = (TextView) parent.findViewById(R.id.card_stats_weightedAverage_title_textView);
        weightedAverageValueTextView = (TextView) parent.findViewById(R.id.card_stats_weightedAverage_value_textView);

        averageTitleTextView = (TextView) parent.findViewById(R.id.card_stats_average_title_textView);
        averageValueTextView = (TextView) parent.findViewById(R.id.card_stats_average_value_textView);

        ratioTitleTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_title_textView);
        ratioValueTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_value_textView);

        if (weightedAverageTitleTextView != null)
            weightedAverageTitleTextView.setText(
                    getContext().getString(R.string.title_weighted_average)
                            + ": "
            );

        if (weightedAverageValueTextView != null)
            weightedAverageValueTextView.setText(subject.getWeightedAverageString());


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
