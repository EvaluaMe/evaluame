package com.garciparedes.evaluame.fragments;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditSubjectFragment extends BaseManageSubjectFragment {


    private static Subject subject;


    /**
     * NewInstance method
     *
     * @return AddSubjectFragment
     */
    public static EditSubjectFragment newInstance(Subject subject1) {
        EditSubjectFragment f = new EditSubjectFragment();
        subject = subject1;
        return f;
    }


    /**
     *
     *
     * @return
     */
    @Override
    public Subject initSubject() {
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
