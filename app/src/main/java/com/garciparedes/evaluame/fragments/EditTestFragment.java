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

    public static final String EXAM = "exam";
    private Exam exam;

    public static EditTestFragment newInstance(Subject subject, Exam exam) {
        EditTestFragment f = new EditTestFragment();
        Bundle args = new Bundle();
        args.putParcelable(SUBJECT, subject);
        args.putParcelable(EXAM, exam);
        f.setArguments(args);
        return f;
    }

    @Override
    public Exam initTest() {

        exam = getArguments().getParcelable(EXAM);
        return exam.copy();
    }

    @Override
    public void setOnClickButton() {

        if((mSubject.getTotalPercentage() - exam.getPercentage() + newExam.getPercentage()) > 100)
            throw new IllegalArgumentException("El porcentaje no puede superar el 100%");

        exam.paste(newExam);
        ListDB.sortExams(mSubject);
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
