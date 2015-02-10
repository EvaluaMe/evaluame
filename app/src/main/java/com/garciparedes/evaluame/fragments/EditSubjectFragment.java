package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Subject;

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
