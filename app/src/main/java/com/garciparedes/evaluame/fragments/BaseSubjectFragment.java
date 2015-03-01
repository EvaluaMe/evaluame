package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends BaseFragment {

    protected Subject subject;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subject = getArguments().getParcelable("subject");

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            subject = savedInstanceState.getParcelable("subject_saved");
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("subject_saved", subject);

    }

}
