package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;

/**
 * Created by garciparedes on 7/2/15.
 */
public class DetailsCard extends Card {

    protected TextView mTitle;
    protected TextView mSecondaryTitle;
    protected RatingBar mRatingBar;
    protected int resourceIdThumbnail;
    protected int count;

    protected String title;
    protected String secondaryTitle;
    protected float rating;


    public DetailsCard(Context context,String title, String secondaryTitle, float rating) {
        super(context, R.layout.card_details);
        init(context);
        this.title = title;
        this.secondaryTitle = secondaryTitle;
        this.rating = rating;
    }

    private void init(Context context){

        //Create a CardHeader
        CardHeader header = new CardHeader(context);
        addCardHeader(header);

        //Add ClickListener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click Listener card=" + getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        setSwipeable(true);
    }


    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        //Retrieve elements
        mTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_title);
        mSecondaryTitle = (TextView) parent.findViewById(R.id.carddemo_myapps_main_inner_secondaryTitle);
        mRatingBar = (RatingBar) parent.findViewById(R.id.carddemo_myapps_main_inner_ratingBar);

        if (mTitle != null)
            mTitle.setText(title);

        if (mSecondaryTitle != null)
            mSecondaryTitle.setText(secondaryTitle);

        if (mRatingBar != null) {
            mRatingBar.setNumStars(5);
            mRatingBar.setMax(5);
            mRatingBar.setStepSize(0.5f);
            mRatingBar.setRating(rating);
        }
    }

    @Override
    public int getType() {
        //Very important with different inner layouts
        return 1;
    }
}
