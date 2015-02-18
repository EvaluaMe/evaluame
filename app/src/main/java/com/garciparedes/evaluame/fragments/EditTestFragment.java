package com.garciparedes.evaluame.fragments;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class EditTestFragment extends BaseManageTestFragment {

    private Exam exam;

    public static EditTestFragment newInstance(Subject subject, Exam exam) {
        EditTestFragment f = new EditTestFragment();
        Bundle args = new Bundle();
        args.putParcelable("subject", subject);
        args.putParcelable("exam", exam);
        f.setArguments(args);
        return f;
    }

    @Override
    public Exam initTest() {

        exam = getArguments().getParcelable("exam");
        return exam;
    }

    @Override
    public void setOnClickButton() {
        exam = newExam;
        ListDB.sortExams(subject);
        ListDB.saveData(getActivity());
    }

    @Override
    public String setTextButton() {
        return getResources().getString(R.string.edit_test);
    }

    @Override
    public String setTextName() {
        return exam.getName();
    }

    @Override
    public String setTextMark() {
        return exam.getMarkString();
    }

    @Override
    public String setTextPercentage() {
        return exam.getPercentageString();
    }
}
