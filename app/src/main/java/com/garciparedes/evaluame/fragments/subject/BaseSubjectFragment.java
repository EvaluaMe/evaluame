package com.garciparedes.evaluame.fragments.subject;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.garciparedes.evaluame.fragments.BaseFragment;
import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends BaseFragment {

    public static final String SUBJECT = "subject";

    private Subject mSubject;

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
        mSubject = getArguments().getParcelable(SUBJECT);
    }

    public Subject getSubject() {
        return mSubject;
    }

    public void setSubject(Subject subject) {
        this.mSubject = subject;
    }
}
