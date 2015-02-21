package com.garciparedes.evaluame.fragments;


import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * FramentAddSubject Class
 */
public class AddSubjectFragment extends BaseManageSubjectFragment {

    /**
     * NewInstance method
     *
     * @return AddSubjectFragment
     */
    public static AddSubjectFragment newInstance() {
        AddSubjectFragment f = new AddSubjectFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }

    @Override
    public void setOnClickButton() {
        if (newSubject.getName().length() <= 0)
            throw new IllegalArgumentException("Introduzca el nombre");
        ListDB.addSubject(getActivity(), newSubject);
    }

    @Override
    public String setTextButton() {
        return getString(R.string.create_subject);
    }

    @Override
    public Subject initNewSubject() {
        return new Subject();
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, DefaultFragment.newInstance())
                .commit();
    }
}
