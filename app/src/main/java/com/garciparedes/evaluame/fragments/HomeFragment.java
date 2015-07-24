package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.cards.GlobalStatsCard;
import com.garciparedes.evaluame.cards.StarredSubjectListCard;
import com.garciparedes.evaluame.cards.UpcomingExamListCard;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;

/**
 * Created by garciparedes on 24/12/14.
 */
public class HomeFragment extends BaseFragment {


    private CardRecyclerView mRecyclerView;
    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private LinearLayoutManager mLayoutManager;

    private ArrayList<Card> mCards;

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();

        return homeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mCards = new ArrayList<Card>();
        mRecyclerView = (CardRecyclerView) view.findViewById(R.id.default_card_list);
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), mCards);
        mLayoutManager = new LinearLayoutManager(getActivity());

        initActionBar(view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);


        customizeActionBar(getResources().getColor(R.color.green_app),getResources().getString(R.string.home),null);
        mCards.add(new StarredSubjectListCard(getActivity(), getFragmentManager()));
        //mCards.add(new BarChartCard(getActivity()));

        mCards.add(new UpcomingExamListCard(getActivity(), getFragmentManager()));
        if (((UpcomingExamListCard) mCards.get(1)).isEmpty()){
            mCards.remove(1);
        }

        //mCards.add(new LineChartCard(getActivity()));
        mCards.add(new GlobalStatsCard(getActivity()));

        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
            if (mRecyclerView.getLayoutManager() == null) {
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
            registerForContextMenu(mRecyclerView);
        }

    }

    @Override
    public void onBackPressed() {
        getActivity().supportFinishAfterTransition();
    }
}
