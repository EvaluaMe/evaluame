package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;


/**
 * Created by garciparedes on 5/12/14.
 */
public class SubjectFragment extends Fragment {


    private TestListFragment testListFragment;

    public static int subjectNum;

    public static SubjectFragment newInstance(int i) {
        SubjectFragment subjectFragment = new SubjectFragment();
        //Bundle args = new Bundle();
        //args.putInt("subject", i);
        subjectNum = i;
        //subjectFragment.setArguments(args);

        return subjectFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        getFragmentManager().beginTransaction()
                .replace(R.id.containerList2, DefaultFragment.newInstance())
                .commit();


        getFragmentManager().beginTransaction()
                .replace(R.id.containerList, TestListFragment.newInstance(0))
                .commit();
    }
}