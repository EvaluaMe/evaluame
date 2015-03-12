package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Mark;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditMarkFragment extends BaseManageTestFragment {

    public static final String EXAM = "mMark";
    private Mark mMark;

    public static EditMarkFragment newInstance(Subject subject, Mark mark) {
        EditMarkFragment f = new EditMarkFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        args.putParcelable(EXAM, mark);
        f.setArguments(args);
        return f;
    }

    @Override
    public Mark initTest() {

        mMark = getArguments().getParcelable(EXAM);
        return mMark.copy();
    }

    @Override
    public void setOnClickButton() {

        if((mSubject.getTotalPercentage() - mMark.getPercentage() + mNewMark.getPercentage()) > 100)
            throw new IllegalArgumentException("El porcentaje no puede superar el 100%");

        mMark.paste(mNewMark);
        ListDB.sortExams(mSubject);
        ListDB.saveData(getActivity());
    }

    @Override
    public String setTextButton() {
        return getResources().getString(R.string.edit_test);
    }

    @Override
    public String setTextName() {
        return mMark.getName();
    }

    @Override
    public String setTextMark() {
        return mMark.getMarkString();
    }

    @Override
    public String setTextPercentage() {
        return mMark.getPercentageString();
    }
}
