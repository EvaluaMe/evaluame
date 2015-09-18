package com.garciparedes.evaluame.fragments.subject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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


    private FloatingActionButton fAButton;
    private FloatingActionButton fAButtonBar;

    private CardArrayRecyclerViewAdapter cardArrayAdapter;
    private CardRecyclerView recyclerView;
    private LinearLayoutManager layoutManager;

    private Exam clickedExam;
    ArrayList<Card> cards;

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
        recyclerView = (CardRecyclerView) view.findViewById(R.id.subject_card_list);
        fAButton = (FloatingActionButton) view.findViewById(R.id.fab);
        fAButtonBar = (FloatingActionButton) view.findViewById(R.id.fab_bar);

        cards = new ArrayList<Card>();

        //Standard array
        cardArrayAdapter = new CardArrayRecyclerViewAdapter(getActivity(), cards);

        layoutManager = new LinearLayoutManager(getActivity());


        initActionBar(view);
        return view;
    }

    @Override
    public void customizeActionBar(int color, String title, String subTitle) {

        if (getToolbar() != null) {

            getToolbar().setTitle(title);

            if (subTitle != null) {
                getToolbar().setSubtitle(subTitle);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().getWindow().setStatusBarColor(ColorUtil.getDarkness(color));
            }
        }

        if(getCollapsingToolbar() != null){
            getCollapsingToolbar().setContentScrimColor(color);
            getCollapsingToolbar().setBackgroundColor(color);
            getCollapsingToolbar().setTitle(title);
            getCollapsingToolbar().setExpandedTitleTextAppearance(R.style.ToolbarExpandedTitle);
        }

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        customizeActionBar(getSubject().getColor(), getSubject().getName(), getSubject().getName());


        //cards.add(new DescriptionCard(getActivity(), mSubject));
        if (getSubject().getWeightedAverage() > 0) {
            cards.add(new PieChartCard(getActivity(), getSubject()));
        }
        cards.add(new StatsSubjectCard(getActivity(), getSubject()));

        for (int i = 0; i < getSubject().getExamList().size(); i++) {
            cards.add(initCard(getSubject().getTestElement(i)));
        }

        if (recyclerView != null) {
            recyclerView.setAdapter(cardArrayAdapter);
            if (recyclerView.getLayoutManager() == null) {
                recyclerView.setLayoutManager(layoutManager);
            }
            registerForContextMenu(recyclerView);
        }


        if (fAButton != null) {
            fAButton.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ColorUtil.getComplimentColor(getSubject().getColor())
                    )
            );
            fAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(AddTestFragment.newInstance(getSubject()));
                }
            });
        }

        if (fAButtonBar != null) {
            fAButtonBar.setBackgroundTintList(
                    ColorStateList.valueOf(
                            ColorUtil.getComplimentColor(getSubject().getColor())
                    )
            );
            fAButtonBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(EditSubjectFragment.newInstance(getSubject()));
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
                changeFragment(EditTestFragment.newInstance(getSubject(), ((ExamCard) card).getExam()));
            }
        });

        card.setOnClickListener(new Card.OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                changeFragment(TestFragment.newInstance(getSubject(), exam));
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
        changeFragment(EditTestFragment.newInstance(getSubject(), clickedExam));
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
                        changeFragment(SubjectFragment.newInstance(getSubject()));
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

                        changeFragment(HomeFragment.newInstance());
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
        changeFragment(EditSubjectFragment.newInstance(getSubject()));
    }

    @Override
    public void onBackPressed() {
        changeFragment(HomeFragment.newInstance());
    }
}