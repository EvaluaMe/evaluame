package com.garciparedes.evaluame.fragments.test;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;


import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.adapters.RecyclerManageTestAdapter;
import com.garciparedes.evaluame.fragments.subject.BaseSubjectFragment;
import com.garciparedes.evaluame.utils.ColorUtil;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.interfaces.AddData;
import com.garciparedes.evaluame.items.Exam;

/**
 *
 */
public abstract class BaseManageTestFragment extends BaseSubjectFragment
        implements AddData {

    private FloatingActionButton mFAButtonBar;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;


    protected Exam newExam;
    private static final String SAVED_NEW_EXAM = "saved_new_exam";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            newExam = savedInstanceState.getParcelable(SAVED_NEW_EXAM);
        } else {
            newExam = initTest();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_NEW_EXAM, newExam);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_test, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_list);
        mLayoutManager = new LinearLayoutManager(getActivity());

        // Set the dialog text -- this is better done in the XML
        mFAButtonBar = (FloatingActionButton) view.findViewById(R.id.fab_bar);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        mAdapter = new RecyclerManageTestAdapter(mSubject, newExam);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        customizeActionBar( mSubject.getColor(), mSubject.getName(), null);

        if (mFAButtonBar != null) {
            mFAButtonBar.setRippleColor(ColorUtil.getComplimentColor(mSubject.getColor()));
            mFAButtonBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {

                        setOnClickButton();
                        hideKeyboard();
                        replaceFragment();

                    } catch (IllegalArgumentException e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
    }

    public abstract Exam initTest();

    public abstract void setOnClickButton();



    /**
     *
     */
    @Override
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(editTextName.getWindowToken(), 0);

    }

    /**
     *
     */
    @Override
    public void replaceFragment() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, TestFragment.newInstance(mSubject, newExam))
                .commit();
    }
}
