package com.garciparedes.evaluame.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.cards.BannerCard;
import com.garciparedes.evaluame.cards.DescriptionCard;
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


    private FloatingActionButton mFAButton;
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
        mListView = (CardListView) view.findViewById(R.id.subject_card_list);
        mFAButton = (FloatingActionButton) view.findViewById(R.id.floating_button);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Card> cards = new ArrayList<Card>();

        cards.add(new DescriptionCard(getActivity(), subject));
        cards.add(new PieChartCard(getActivity(), subject));
        cards.add(new StatsCard(getActivity(), subject));
        //cards.add(new ExamListCard(getActivity(), subject));

        for (int i = 0; i < subject.getExamList().size(); i++) {
            cards.add(initCard(subject.getTestElement(i)));
        }
        cards.add(new BannerCard(getActivity()));

        //Standard array
        mCardArrayAdapter = new CardArrayAdapter(getActivity(), cards);

        if (mCardArrayAdapter != null) {
            mCardArrayAdapter.setEnableUndo(true);
        }


        if (mListView != null) {
            mListView.setAdapter(mCardArrayAdapter);
        }

        if (mFAButton != null) {

            mFAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, AddTestFragment.newInstance(subject))
                            .commit();

                }
            });
        }
    }

    public ExamCard initCard(Exam exam) {
        // Create a Card
        ExamCard card = new ExamCard(getActivity(), exam);

        card.setSwipeable(true);
        card.setId(exam.getName());

        card.getCardHeader().setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, EditTestFragment.newInstance(subject, ((ExamCard) card).getExam()))
                        .commit();

            }
        });

        card.setSwipeable(true);

        card.setOnSwipeListener(new Card.OnSwipeListener() {
            @Override
            public void onSwipe(Card card) {
                mFAButton.hide(true);
            }
        });

        card.setOnUndoSwipeListListener(new Card.OnUndoSwipeListListener() {
            @Override
            public void onUndoSwipe(Card card) {
                mFAButton.show();
            }
        });

        card.setOnUndoHideSwipeListListener(new Card.OnUndoHideSwipeListListener() {
            @Override
            public void onUndoHideSwipe(Card card) {
                ListDB.removeTest(getActivity(), subject,((ExamCard) card).getExam());
                mFAButton.show(true);
            }
        });
        return card;
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_subject) {

            deleteSubject();

            return true;
        }


        if (item.getItemId() == R.id.action_edit_subject) {

            editSubject();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     *
     */
    public void deleteSubject() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(getString(R.string.delete_subject));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.delete_subject_confirmation))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity

                        ListDB.removeSubject(getActivity(), subject);
                        //updateListView();
                        ((MainActivity) getActivity()).update();

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, DefaultFragment.newInstance())
                                .commit();


                    }
                })
                .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        inflater.inflate(R.menu.menu_subject, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     *
     */
    public void editSubject() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditSubjectFragment.newInstance(subject))
                .commit();

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, DefaultFragment.newInstance())
                .commit();
    }
}