package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends BaseFragment {

    public static final String SUBJECT = "mSubject";
    public static final String SUBJECT_SAVED = "subject_saved";

    protected Subject mSubject;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mSubject = savedInstanceState.getParcelable(SUBJECT_SAVED);
        } else {
            mSubject = getArguments().getParcelable(SUBJECT);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SUBJECT_SAVED, mSubject);

    }

}
