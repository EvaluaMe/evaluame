package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 8/2/15.
 */
public class ExamCard extends Card {

    private ProgressBar markProgressBar;
    private TextView markTextView;
    private TextView percentageTextView;

    private Exam exam;


    public ExamCard(FragmentActivity context, Exam exam){
        super(context, R.layout.card_test);
        this.exam = exam;
        init(context);
    }

    public Exam getExam() {
        return exam;
    }

    private void init(Context context){

        //Create a CardHeader
        CardHeader header = new CardHeader(context);
        header.setTitle(exam.getName());
        //header.setButtonExpandVisible(true);
        header.setOtherButtonVisible(true);

        //Set visible the expand/collapse button
        header.setOtherButtonVisible(true);
        header.setOtherButtonDrawable(R.drawable.ic_action_edit);
        addCardHeader(header);

    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        markProgressBar = (ProgressBar) parent.findViewById(R.id.card_test_mark_progressBar);
        markTextView = (TextView) parent.findViewById(R.id.card_test_mark_textView);
        percentageTextView = (TextView) parent.findViewById(R.id.card_test_percentage_textView);

        if(markTextView != null){
            markTextView.setText(exam.getMarkString());
        }

        if (percentageTextView != null) {
            percentageTextView.setText(exam.getPercentageString());
        }

        if (markProgressBar != null) {
            markProgressBar.setMax(10);
            markProgressBar.setProgress((int)(exam.getMark()));

        }
    }

}
