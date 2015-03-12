package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Mark;
import com.garciparedes.evaluame.items.Subject;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 8/2/15.
 */
public class ExamCard extends Card {

    private ProgressBar mMarkProgressBar;
    private TextView mMarkTextView;
    private TextView mPercentageTextView;
    private TextView mDateTextView;
    private TextView mTypeTextView;

    private Subject subject;
    private Mark mMark;


    public ExamCard(FragmentActivity context,Subject subject, Mark mark) {
        super(context, R.layout.card_test);
        this.mMark = mark;
        this.subject = subject;
        init(context);
    }

    public Mark getMark() {
        return mMark;
    }

    private void init(Context context) {

        //Create a CardHeader
        CustomCardHeader header = new CustomCardHeader(context, subject.getColor());
        header.setTitle(mMark.getName());
        addCardHeader(header);

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mMarkProgressBar = (ProgressBar) parent.findViewById(R.id.card_test_mark_progressBar);
        mMarkTextView = (TextView) parent.findViewById(R.id.card_test_mark_textView);
        mPercentageTextView = (TextView) parent.findViewById(R.id.card_test_percentage_textView);
        mDateTextView = (TextView) parent.findViewById(R.id.card_test_date_textView);
        mTypeTextView = (TextView) parent.findViewById(R.id.card_test_type_textView);

        if (mMarkTextView != null) {
            mMarkTextView.setText(mMark.getMarkString());
        }

        if (mPercentageTextView != null) {
            mPercentageTextView.setText(mMark.getPercentageString());
        }

        if (mDateTextView != null) {
            mDateTextView.setText(mMark.getDateString(getContext()));
        }

        if (mTypeTextView != null) {
            mTypeTextView.setText(mMark.getTypeString(getContext()));
        }

        if (mMarkProgressBar != null) {
            mMarkProgressBar.setMax(10);
            mMarkProgressBar.setProgress((int) (mMark.getValue()));

        }
    }

}
