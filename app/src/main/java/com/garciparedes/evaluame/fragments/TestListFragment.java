package com.garciparedes.evaluame.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.adapters.TestListAdapter;
import com.garciparedes.evaluame.items.Test;
import com.garciparedes.evaluame.provider.ListDB;


import java.util.ArrayList;

/**
 * Created by garciparedes on 6/2/15.
 */
public class TestListFragment extends Fragment {


    private ListView listView;
    private TestListAdapter testListAdapter;
    public static TestListFragment newInstance(int subjectNum) {
        TestListFragment f = new TestListFragment();
        Bundle args = new Bundle();
        args.putInt("subject", subjectNum);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_list, container, false);

        listView = (ListView) view.findViewById(R.id.containerL);

        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Test> arrayList = new ArrayList<>();
        arrayList.add(new Test("hola",4,5));
        arrayList.add(new Test("hola",4,5));
        arrayList.add(new Test("hola",4,5));


        testListAdapter = new TestListAdapter(this, arrayList);
        listView.setAdapter(testListAdapter);
    }

}

