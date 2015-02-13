package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.cards.DetailsCard;
import com.garciparedes.evaluame.cards.ExamCard;
import com.garciparedes.evaluame.cards.PieChartCard;
import com.garciparedes.evaluame.cards.StatsCard;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.view.CardListView;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends BaseSubjectFragment {


    private FloatingActionButton button;

    private CardArrayAdapter mCardArrayAdapter;

    private CardListView mListView;

    public static SubjectFragment newInstance(Subject subject) {
        SubjectFragment subjectFragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putParcelable("subject", subject);
        subjectFragment.setArguments(args);
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

        ArrayList<Card> cards = new ArrayList<Card>();

        cards.add(new DetailsCard(getActivity(), subject));

        cards.add(new PieChartCard(getActivity(), subject));

        cards.add(new StatsCard(getActivity(), subject));

        //cards.add(new ExamListCard(getActivity(), subject));

        for (int i = 0; i < subject.getExamList().size(); i++) {
            cards.add(initCard(subject.getTestElement(i)));
        }

        //Standard array
        mCardArrayAdapter = new CardArrayAdapter(getActivity(),cards);

        mCardArrayAdapter.setEnableUndo(true);



        mListView = (CardListView) getActivity().findViewById(R.id.subject_card_list);
        if (mListView!=null){
            mListView.setAdapter(mCardArrayAdapter);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction()
                        .replace(R.id.container, AddTestFragment.newInstance(subject))
                        .commit();

            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mCardArrayAdapter.getUndoBarController().onSaveInstanceState(outState);
    }


    public ExamCard initCard(Exam exam){
        // Create a Card
        ExamCard card = new ExamCard(getActivity(), exam);

        card.setSwipeable(true);
        card.setId(exam.getName());

        card.getCardHeader().setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container,
                                EditTestFragment.newInstance(subject, ((ExamCard) card).getExam()))
                        .commit();

            }
        });

        card.setSwipeable(true);

        card.setOnSwipeListener(new Card.OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                button.hide(true);
            }
        });

        card.setOnUndoSwipeListListener(new Card.OnUndoSwipeListListener() {
            @Override
            public void onUndoSwipe(Card card) {
                button.show();
            }
        });

        card.setOnUndoHideSwipeListListener(new Card.OnUndoHideSwipeListListener() {
            @Override
            public void onUndoHideSwipe(Card card) {
                subject.removeTest( ((ExamCard) card).getExam() );
                ListDB.saveData(getActivity());
                button.show(true);
            }
        });
        return card;
    }

}