package com.garciparedes.evaluame.fragments.subject;

import android.os.Bundle;

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
        args.putParcelable(SUBJECT, subject);
        f.setArguments(args);
        return f;
    }

    /**
     * @return
     */
    @Override
    public Subject initNewSubject() {
        return getSubject().copy();
    }

    /**
     *
     */
    @Override
    public void setOnClickButton() {
        getSubject().paste(newSubject);
        ListDB.saveData(getActivity());
    }

    @Override
    public void onBackPressed() {
        changeFragment(SubjectFragment.newInstance(getSubject()));
    }
}
