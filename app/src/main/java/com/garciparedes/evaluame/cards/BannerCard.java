package com.garciparedes.evaluame.cards;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import it.gmariotti.cardslib.library.internal.Card;

import com.garciparedes.evaluame.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
/**
 * Created by garciparedes on 20/2/15.
 */
public class BannerCard extends Card {

    private AdView mAdView;
    public BannerCard(Context context) {
        super(context, R.layout.card_banner);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        mAdView = (AdView) view.findViewById(R.id.adView);
        //AdSize adSize = new AdSize(AdSize.FULL_WIDTH, AdSize.AUTO_HEIGHT);
        //mAdView.setAdSize(adSize);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        super.setupInnerViewElements(parent, view);
    }
}
