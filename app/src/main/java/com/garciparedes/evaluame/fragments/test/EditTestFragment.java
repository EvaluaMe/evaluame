package com.garciparedes.evaluame.fragments.test;

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
    public static final String SAVED_EXAM = "saved_exam";

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null){
            exam = savedInstanceState.getParcelable(SAVED_EXAM);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_EXAM, exam);
    }


    @Override
    public Exam initTest() {

        exam = getArguments().getParcelable(EXAM);
        return exam.copy();
    }

    @Override
    public void setOnClickButton() {

        /*
        if((mSubject.getTotalPercentage() - exam.getPercentage() + newExam.getPercentage()) > 100)
            throw new IllegalArgumentException(getString(R.string.fail_max_percent));
        */

        exam.paste(newExam);
        ListDB.sortExams(getSubject());
        ListDB.saveData(getActivity());
    }


    @Override
    public void onBackPressed() {
        changeFragment(TestFragment.newInstance(getSubject(), exam));
    }
}
