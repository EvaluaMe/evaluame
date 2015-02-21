package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditSubjectFragment extends BaseManageSubjectFragment {

    /**
     * NewInstance method
     *
     * @return AddSubjectFragment
     */
    public static EditSubjectFragment newInstance(Subject subject) {
        EditSubjectFragment f = new EditSubjectFragment();
        Bundle args = new Bundle();
        args.putParcelable("subject", subject);
        f.setArguments(args);
        return f;
    }

    /**
     * @return
     */
    @Override
    public Subject initNewSubject() {
        return subject;
    }

    /**
     *
     */
    @Override
    public void setOnClickButton() {
        if (newSubject.getName().length() <= 0)
            throw new IllegalArgumentException("Introduzca el nombre");

        subject = newSubject;
        ListDB.saveData(getActivity());
    }

    /**
     * @return
     */
    @Override
    public String setTextButton() {
        return getResources().getString(R.string.edit_subject);
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(subject))
                .commit();
    }
}
