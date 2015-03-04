package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;

/**
 * Created by garciparedes on 24/2/15.
 */
public class ExamFragment extends BaseSubjectFragment{

    private static final String EXAM = "exam";
    private static final String EXAM_SAVED = "exam_saved";

    private Exam mExam;

    public static ExamFragment newInstance(Subject subject, Exam exam) {
        ExamFragment examFragment = new ExamFragment();
        Bundle args = new Bundle();
        args.putParcelable("mSubject", subject);
        args.putParcelable(EXAM, exam);
        examFragment.setArguments(args);
        return examFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mExam = savedInstanceState.getParcelable(EXAM_SAVED);
        } else {
            mExam = getArguments().getParcelable(EXAM);
        }

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(EXAM_SAVED, mExam);

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(mSubject))
                .commit();
    }
}
