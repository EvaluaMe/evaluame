package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 13/2/15.
 */
public abstract class BaseSubjectFragment extends Fragment {

    protected Subject subject;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        subject = initSubject();
    }

    /**
     * @return
     */
    public Subject initSubject() {
        Subject subject;
        try {
            subject = getArguments().getParcelable("subject");
        } catch (NullPointerException e) {
            subject = null;
        }
        return subject;
    }
}
