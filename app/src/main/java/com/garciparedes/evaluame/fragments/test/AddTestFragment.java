package com.garciparedes.evaluame.fragments.test;

import android.os.Bundle;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.subject.SubjectFragment;
import com.garciparedes.evaluame.items.Exam;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 10/2/15.
 */
public class AddTestFragment extends BaseManageTestFragment {


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
        ListDB.addTest(getActivity(), getSubject(), newExam);
    }


    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, SubjectFragment.newInstance(getSubject()))
                .commit();
    }
}
