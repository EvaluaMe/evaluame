package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.Util.Number;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

import java.util.Calendar;

import it.gmariotti.cardslib.library.internal.Card;

/**
 * Created by garciparedes on 22/2/15.
 */
public class GlobalStatsCard extends Card {

    private TextView weightedAverageTitleTextView;
    private TextView weightedAverageValueTextView;

    private TextView averageTitleTextView;
    private TextView averageValueTextView;

    private TextView ratioTitleTextView;
    private TextView ratioValueTextView;

    private float average;
    private float ratio;


    public GlobalStatsCard(Context context) {
        super(context, R.layout.card_global_stats);
        init(context);
        calcule();

    }

    private void init(Context context) {

        //Create a CardHeader
        CustomCardHeader header = new CustomCardHeader(context);
        header.setTitle(context.getString(R.string.stats));
        //header.setButtonExpandVisible(true);
        addCardHeader(header);



    }

    private void calcule(){
        Calendar now = Calendar.getInstance();

        int count = 0, pass = 0;
        float total=0;
        for(int i = 0; i < ListDB.getMasterList().size(); i++){
            Subject subject = ListDB.get(i);

            for (int j = 0; j < subject.getExamList().size(); j++) {

                Exam exam = subject.getTestElement(j);
                try {
                    if (exam.getDate().before(now)) {
                        total += exam.getMark();

                        if (exam.getMark() >= 5) {
                            pass++;
                        }
                        count++;
                    }
                }catch (NullPointerException ignored){};
            }
        }
        if (count != 0){
            average = (total/count);
            ratio = ((float) pass/count);
        }
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        averageTitleTextView = (TextView) parent.findViewById(R.id.card_stats_average_title_textView);
        averageValueTextView = (TextView) parent.findViewById(R.id.card_stats_average_value_textView);

        ratioTitleTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_title_textView);
        ratioValueTextView = (TextView) parent.findViewById(R.id.card_stats_ratio_value_textView);



        if (averageTitleTextView != null)
            averageTitleTextView.setText(
                    getContext().getString(R.string.average)
                            + ": "
            );

        if (averageValueTextView != null)
            averageValueTextView.setText(com.garciparedes.evaluame.Util.Number.toString(average));


        if (ratioTitleTextView != null)
            ratioTitleTextView.setText(
                    getContext().getString(R.string.ratio)
                            + ": "
            );

        if (ratioValueTextView != null)
            ratioValueTextView.setText(Number.toString(ratio));
    }

}
