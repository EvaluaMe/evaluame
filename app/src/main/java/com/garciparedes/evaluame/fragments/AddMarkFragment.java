package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Mark;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class AddMarkFragment extends BaseManageTestFragment {

    private final String defaultText = "- - . - -";

    public static AddMarkFragment newInstance(Subject subject) {
        AddMarkFragment f = new AddMarkFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        f.setArguments(args);
        return f;
    }


    @Override
    public Mark initTest() {
        return new Mark();
    }

    @Override
    public void setOnClickButton() {
        ListDB.addTest(getActivity(), mSubject, mNewMark);
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
