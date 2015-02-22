package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.base.BaseCard;

/**
 * Created by garciparedes on 8/2/15.
 */
public class ExamCard extends Card {

    private ProgressBar mMarkProgressBar;
    private TextView mMarkTextView;
    private TextView mPercentageTextView;
    private TextView mDateTextView;
    private TextView mTypeTextView;

    private Exam exam;


    public ExamCard(FragmentActivity context, Exam exam) {
        super(context, R.layout.card_test);
        this.exam = exam;
        init(context);
    }

    public Exam getExam() {
        return exam;
    }

    private void init(Context context) {

        //Create a CardHeader
        CardHeader header = new CardHeader(context);
        header.setTitle(exam.getName());
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
            mMarkTextView.setText(exam.getMarkString());
        }

        if (mPercentageTextView != null) {
            mPercentageTextView.setText(exam.getPercentageString());
        }

        if (mDateTextView != null) {
            mDateTextView.setText(exam.getDateString(getContext()));
        }

        if (mTypeTextView != null) {
            mTypeTextView.setText(exam.getTypeString(getContext()));
        }

        if (mMarkProgressBar != null) {
            mMarkProgressBar.setMax(10);
            mMarkProgressBar.setProgress((int) (exam.getMark()));

        }
    }

}
