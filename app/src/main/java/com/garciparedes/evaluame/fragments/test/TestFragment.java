package com.garciparedes.evaluame.fragments.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;

import com.garciparedes.evaluame.adapters.RecyclerAtribImageTextAdapter;
import com.garciparedes.evaluame.fragments.subject.BaseSubjectFragment;
import com.garciparedes.evaluame.fragments.subject.SubjectFragment;
import com.garciparedes.evaluame.utils.ColorUtil;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.garciparedes.evaluame.utils.ListAtrib;

/**
 * Created by garciparedes on 24/2/15.
 */
public class TestFragment extends BaseSubjectFragment {

    private static final String EXAM = "exam";
    private static final String EXAM_SAVED = "exam_saved";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    private FloatingActionButton mFAButtonBar;

    private Exam mExam;

    public static TestFragment newInstance(Subject subject, Exam exam) {
        TestFragment testFragment = new TestFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        args.putParcelable(EXAM, exam);
        testFragment.setArguments(args);
        return testFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_atrib_list);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mFAButtonBar = (FloatingActionButton) view.findViewById(R.id.fab_bar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





        if (savedInstanceState != null) {
            mExam = savedInstanceState.getParcelable(EXAM_SAVED);
        } else {
            mExam = getArguments().getParcelable(EXAM);
        }

        mAdapter = new RecyclerAtribImageTextAdapter(ListAtrib.generate(getActivity(), mExam));

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        customizeActionBar(mSubject.getColor(), mExam.getName(), mSubject.getName());


        if (mFAButtonBar != null) {
            mFAButtonBar.setRippleColor(ColorUtil.getComplimentColor(mSubject.getColor()));
            //mFAButton.attachToRecyclerView(mRecyclerView);
            mFAButtonBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, EditTestFragment.newInstance(mSubject, mExam))
                            .commit();

                }
            });
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXAM_SAVED, mExam);

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                .commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    /**
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {

            deleteMark();

            return true;
        }


        if (item.getItemId() == R.id.action_edit) {

            editMark();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
                        ListDB.removeTest(getActivity(), mSubject, mExam);

                        getFragmentManager().beginTransaction()
                                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
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
    public void editMark() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, EditTestFragment.newInstance(mSubject, mExam))
                .commit();

    }
}
