package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.cards.BarChartCard;
import com.garciparedes.evaluame.cards.GlobalStatsCard;
import com.garciparedes.evaluame.cards.StarredSubjectListCard;
import com.garciparedes.evaluame.cards.UpcomingExamListCard;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by garciparedes on 24/12/14.
 */
public class DefaultFragment extends BaseFragment {

    public static DefaultFragment newInstance() {
        DefaultFragment defaultFragment = new DefaultFragment();

        return defaultFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_default, container, false);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        ArrayList<Card> cards = new ArrayList<>();

        cards.add(new StarredSubjectListCard(getActivity(), getFragmentManager()));
        //cards.add(new BarChartCard(getActivity()));

        cards.add(new UpcomingExamListCard(getActivity(), getFragmentManager()));
        if (((UpcomingExamListCard) cards.get(1)).isEmpty()){
            cards.remove(1);
        }

        //mCards.add(new LineChartCard(getActivity()));
        cards.add(new GlobalStatsCard(getActivity()));

        //Standard array
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        // Define your sections
        List<CardSection> sections = new ArrayList<CardSection>();
        //sections.add(new CardSection(0,"Section 1"));
        //sections.add(new CardSection(3,"Section 2"));
        CardSection[] dummy = new CardSection[sections.size()];

        //Define your Sectioned adapter
        SectionedCardAdapter mAdapter = new SectionedCardAdapter(getActivity(), mCardArrayAdapter);
        mAdapter.setCardSections(sections.toArray(dummy));

        CardListView listView = (CardListView) getActivity().findViewById(R.id.default_card_list);
        if (listView != null) {
            //Use the external adapter.
            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
        }

    }

    @Override
    public void onBackPressed() {
        getActivity().supportFinishAfterTransition();
    }
}
