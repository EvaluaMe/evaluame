package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditSubjectFragment extends BaseManageSubjectFragment {


    private Subject subject;


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
     *
     *
     * @return
     */
    @Override
    public Subject initSubject() {
        subject = getArguments().getParcelable("subject");
        return subject;
    }


    /**
     *
     */
    @Override
    public void setOnClickButton() {
        subject = newSubject;
        ListDB.saveData(getActivity());
    }


    /**
     *
     * @return
     */
    @Override
    public String setTextButton() {
        return getResources().getString(R.string.edit_subject);
    }


    /**
     *
     * @return
     */
    @Override
    public String setTextName() {
        return subject.getName();
    }


    /**
     *
     * @return
     */
    @Override
    public String setTextDescription() {
        return subject.getDescription();
    }
}
