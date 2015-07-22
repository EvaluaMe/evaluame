package com.garciparedes.evaluame.fragments.subject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.cards.ExamCard;
import com.garciparedes.evaluame.cards.PieChartCard;
import com.garciparedes.evaluame.cards.StatsSubjectCard;
import com.garciparedes.evaluame.fragments.HomeFragment;
import com.garciparedes.evaluame.fragments.test.AddTestFragment;
import com.garciparedes.evaluame.fragments.test.EditTestFragment;
import com.garciparedes.evaluame.fragments.test.TestFragment;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.garciparedes.evaluame.utils.ColorUtil;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.recyclerview.internal.CardArrayRecyclerViewAdapter;
import it.gmariotti.cardslib.library.recyclerview.view.CardRecyclerView;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends BaseSubjectFragment {


    private FloatingActionButton mFAButton;
    private FloatingActionButton mFAButtonBar;

    private CardArrayRecyclerViewAdapter mCardArrayAdapter;
    private CardRecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    private Exam clickedExam;
    ArrayList<Card> mCards;

    public static SubjectFragment newInstance(Subject subject) {
        SubjectFragment subjectFragment = new SubjectFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        subjectFragment.setArguments(args);
        return subjectFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);
        mRecyclerView = (CardRecyclerView) view.findViewById(R.id.subject_card_list);
        mFAButton = (FloatingActionButton) view.findViewById(R.id.fab);
        mFAButtonBar = (FloatingActionButton) view.findViewById(R.id.fab_bar);

        mCards = new ArrayList<Card>();

        //Standard array
        mCardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), mCards);

        mLayoutManager = new LinearLayoutManager(getActivity());


        initToolbar(view);
        return view;
    }

    @Override
    public void customizeActionBar(int color, String title, String subTitle) {

        final TextView expandedTextView = (TextView) getCollapsingToolbar().findViewById(R.id.expanded_toolbar_title);
        final TextView collapsedTextView = (TextView) getToolbar().findViewById(R.id.collapsed_toolbar_title);
        collapsedTextView.setVisibility(View.GONE);

        expandedTextView.setText(title);
        collapsedTextView.setText(title);


        if (getToolbar() != null) {
            getToolbar().setBackgroundColor(color);

            if (subTitle != null) {
                getToolbar().setSubtitle(subTitle);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().getWindow().setStatusBarColor(ColorUtil.getDarkness(color));
            }
        }

        if(getCollapsingToolbar() != null){
            getCollapsingToolbar().setBackgroundColor(color);
            getCollapsingToolbar().setDrawingCacheBackgroundColor(color);
            getCollapsingToolbar().setExpandedTitleTextAppearance(R.style.ToolbarExpandedTitle);
            //collapsingToolbar.setStatusBarScrimColor(color);
            getCollapsingToolbar().setContentScrimColor(color);
        }

        getCoordinatorLayout().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                if (mFAButtonBar.isShown()) {
                    collapsedTextView.setVisibility(View.GONE);
                } else {
                    collapsedTextView.setVisibility(View.VISIBLE);

                }
            }
        });

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        customizeActionBar(getSubject().getColor(), getSubject().getName(), getSubject().getName());


        //mCards.add(new DescriptionCard(getActivity(), mSubject));
        if (getSubject().getWeightedAverage() > 0) {
            mCards.add(new PieChartCard(getActivity(), getSubject()));
        }
        mCards.add(new StatsSubjectCard(getActivity(), getSubject()));

        for (int i = 0; i < getSubject().getExamList().size(); i++) {
            mCards.add(initCard(getSubject().getTestElement(i)));
        }

        if (mRecyclerView != null) {
            mRecyclerView.setAdapter(mCardArrayAdapter);
            if (mRecyclerView.getLayoutManager() == null) {
                mRecyclerView.setLayoutManager(mLayoutManager);
            }
            registerForContextMenu(mRecyclerView);
        }


        if (mFAButton != null) {
            mFAButton.setRippleColor(ColorUtil.getComplimentColor(getSubject().getColor()));
            mFAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, AddTestFragment.newInstance(getSubject()))
                            .commit();

                }
            });
        }

        if (mFAButtonBar != null) {
            mFAButtonBar.setRippleColor(ColorUtil.getComplimentColor(getSubject().getColor()));
            mFAButtonBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, EditSubjectFragment.newInstance(getSubject()))
                            .commit();

                }
            });
        }
    }

    public ExamCard initCard(final Exam exam) {
        // Create a Card
        ExamCard card = new ExamCard(getActivity(), getSubject(), exam);

        card.setSwipeable(true);
        card.setId(exam.getName());


        card.getCardHeader().setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, EditTestFragment.newInstance(getSubject(), ((ExamCard) card).getExam()))
                        .commit();

            }
        });

        card.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, TestFragment.newInstance(getSubject(), exam))
                        .commit();
            }
        });
        card.setLongClickable(true);
        card.setOnLongClickListener(new Card.OnLongCardClickListener() {
            @Override
            public boolean onLongClick(Card card, View view) {
                clickedExam =((ExamCard) card).getExam();
                return false;
            }
        });


        return card;
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.edit, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit:
                editMark();
                return true;

            case R.id.action_delete:
                deleteMark();
                return true;

            default:
                return super.onContextItemSelected(item);

        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.star, menu);
        inflater.inflate(R.menu.edit, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (getSubject() != null) {
            if (getSubject().isStarred()) {
                menu.getItem(0).setIcon(R.drawable.ic_action_important_dark);
            }
        }

        super.onPrepareOptionsMenu(menu);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {

            deleteSubject();

            return true;
        }


        if (item.getItemId() == R.id.action_edit) {

            editSubject();

            return true;
        }

        if (item.getItemId() == R.id.action_starred) {

            starSubject(item);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public  void starSubject(MenuItem item){
        if (getSubject().isStarred()) {
            getSubject().setStarred(false);
            item.setIcon(getResources().getDrawable(R.drawable.ic_action_not_important_dark));
            ListDB.saveData(getActivity());
        } else {
            getSubject().setStarred(true);
            item.setIcon(getResources().getDrawable(R.drawable.ic_action_important_dark));
            ListDB.saveData(getActivity());

        }
    }


    public void editMark() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditTestFragment.newInstance(getSubject(), clickedExam))
                .commit();

    }

    public void deleteMark(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle(getString(R.string.delete_mark));

        // set dialog message
        alertDialogBuilder
                .setMessage(getString(R.string.delete_mark_confirmation))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, close
                        // current activity
                        ListDB.removeTest(getActivity(), getSubject(), clickedExam);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, SubjectFragment.newInstance(getSubject()))
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

                        ListDB.removeSubject(getActivity(), getSubject());

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, HomeFragment.newInstance())
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

    /**
     *
     */
    public void editSubject() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditSubjectFragment.newInstance(getSubject()))
                .commit();

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit();
    }
}