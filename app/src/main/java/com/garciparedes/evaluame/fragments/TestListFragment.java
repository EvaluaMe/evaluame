package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;


import java.util.ArrayList;

/**
 * Created by garciparedes on 6/2/15.
 */
public class TestListFragment extends Fragment {


    private ListView listView;
    //private TestListAdapter testListAdapter;
    public static TestListFragment newInstance(int subjectNum) {
        TestListFragment f = new TestListFragment();
        Bundle args = new Bundle();
        args.putInt("subject", subjectNum);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_list, container, false);


        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int listImages[] = new int[]{R.drawable.angry_1, R.drawable.angry_1,
                R.drawable.angry_1, R.drawable.angry_1, R.drawable.angry_1};

        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i<5; i++) {
            // Create a Card
            Card card = new Card(getActivity());
            // Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            // Add Header to card
            header.setTitle("Angry bird: " + i);
            card.setTitle("sample title");
            card.addCardHeader(header);

            CardThumbnail thumb = new CardThumbnail(getActivity());
            thumb.setDrawableResource(listImages[i]);
            //card.addCardThumbnail(thumb);

            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        CardListView listView = (CardListView) getView().findViewById(R.id.myList);
        if (listView != null) {
            listView.setAdapter(mCardArrayAdapter);
        }
    }

}

