package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class AddTestFragment extends BaseManageTestFragment {

    private final String defaultText = "- - . - -";

    public static AddTestFragment newInstance(Subject subject) {
        AddTestFragment f = new AddTestFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        f.setArguments(args);
        return f;
    }


    @Override
    public Exam initTest() {
        return new Exam();
    }

    @Override
    public void setOnClickButton() {
        ListDB.addTest(getActivity(), mSubject, newExam);
    }

    @Override
    public String setTextButton() {
        return getString(R.string.create_test);
    }

    @Override
    public String setTextName() {
        return null;
    }

    @Override
    public String setTextMark() {
        return defaultText;
    }

    @Override
    public String setTextPercentage() {
        return defaultText;
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                .commit();
    }
}
