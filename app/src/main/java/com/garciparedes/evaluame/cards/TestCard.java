package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Test;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardExpand;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 8/2/15.
 */
public class TestCard extends Card {

    private RatingBar markRatingBar;
    private TextView markTextView;
    private TextView percentageTextView;

     Test test;


    public TestCard(Context context, Test test){
        super(context, R.layout.card_test);
        this.test = test;
        init(context);
    }


    private void init(Context context){

        //Create a CardHeader
        CardHeader header = new CardHeader(context);
        header.setTitle(test.getName());
        header.setButtonExpandVisible(true);

        addCardHeader(header);

        CardExpand cardExpand = new CardExpand(context);
        cardExpand.setTitle("hooooooooola");
        addCardExpand(cardExpand);



        setSwipeable(true);

        setOnSwipeListener(new OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                Toast.makeText(getContext(), "Removed card=" + test.getName(), Toast.LENGTH_SHORT).show();
            }
        });


        setOnUndoSwipeListListener(new OnUndoSwipeListListener() {
            @Override
            public void onUndoSwipe(Card card) {
                Toast.makeText(getContext(), "Undo card=" + test.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        setOnUndoHideSwipeListListener(new OnUndoHideSwipeListListener() {
            @Override
            public void onUndoHideSwipe(Card card) {
                Toast.makeText(getContext(), "Hide undo card=" + test.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        markRatingBar = (RatingBar) parent.findViewById(R.id.card_test_mark_ratingBar);
        markTextView = (TextView) parent.findViewById(R.id.card_test_mark_textView);
        percentageTextView = (TextView) parent.findViewById(R.id.card_test_percentage_textView);

        if(markTextView != null){
            markTextView.setText(test.getMarkString());
        }

        if (percentageTextView != null) {
            percentageTextView.setText(test.getPercentageString());
        }

        if (markRatingBar != null) {
            markRatingBar.setNumStars(5);
            markRatingBar.setMax(5);
            markRatingBar.setStepSize(0.5f);
            markRatingBar.setRating((test.getMark()/2));

        }
    }

}
