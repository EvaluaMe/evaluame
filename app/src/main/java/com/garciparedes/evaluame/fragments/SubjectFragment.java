package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.cards.ChartCard;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.prototypes.CardSection;
import it.gmariotti.cardslib.library.prototypes.SectionedCardAdapter;
import it.gmariotti.cardslib.library.view.CardListView;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends Fragment {



    public static int subjectNum;

    private FloatingActionButton button;


    public static SubjectFragment newInstance(int i) {
        SubjectFragment subjectFragment = new SubjectFragment();
        //Bundle args = new Bundle();
        //args.putInt("subject", i);
        subjectNum = i;
        //subjectFragment.setArguments(args);

        return subjectFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        button = (FloatingActionButton) view.findViewById(R.id.floating_button);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int listImages[] = new int[]{R.drawable.angry_1, R.drawable.angry_1,
                R.drawable.angry_1, R.drawable.angry_1, R.drawable.angry_1};

        ArrayList<Card> cards = new ArrayList<Card>();

        cards.add(new ChartCard(getActivity()));

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
            card.addCardThumbnail(thumb);

            cards.add(card);
        }

        //Standard array
        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        // Define your sections
        List<CardSection> sections =  new ArrayList<CardSection>();
        sections.add(new CardSection(0,"Section 1"));
        sections.add(new CardSection(3,"Section 2"));
        CardSection[] dummy = new CardSection[sections.size()];

        //Define your Sectioned adapter
        SectionedCardAdapter mAdapter = new SectionedCardAdapter(getActivity(), mCardArrayAdapter);
        mAdapter.setCardSections(sections.toArray(dummy));

        CardListView listView = (CardListView) getActivity().findViewById(R.id.myList);
        if (listView!=null){
            //Use the external adapter.
            listView.setExternalAdapter(mAdapter, mCardArrayAdapter);
        }

        button.attachToListView(listView);
        button.bringToFront();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, AddTestFragment.newInstance(subjectNum)).commit();

            }
        });
    }
}