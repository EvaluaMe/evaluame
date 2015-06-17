package com.garciparedes.evaluame.fragments.subject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.BaseFragment;
import com.garciparedes.evaluame.fragments.HomeFragment;
import com.garciparedes.evaluame.utils.ColorUtil;
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.adapters.RecyclerSubjectListAdapter;
import com.garciparedes.evaluame.provider.ListDB;

/**
 * Created by garciparedes on 12/6/15.
 */
public class SubjectListFragment extends BaseFragment {


    private RecyclerView recyclerSubjetList;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton mFAButton;


    public static SubjectListFragment newInstance() {
        SubjectListFragment subjectListFragment = new SubjectListFragment();
        return subjectListFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_subject_list, container, false);

        recyclerSubjetList = (RecyclerView) view.findViewById(R.id.recycler_subject_list);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        // specify an adapter (see also next example)
        mAdapter = new RecyclerSubjectListAdapter(ListDB.getMasterList());

        mFAButton = (FloatingActionButton) view.findViewById(R.id.fab);

        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        customizeActionBar(getResources().getColor(R.color.green_app)
                , getResources().getString(R.string.subjects)
                , null
        );

        recyclerSubjetList.setHasFixedSize(true);
        recyclerSubjetList.setLayoutManager(mLayoutManager);
        recyclerSubjetList.setAdapter(mAdapter);

        if (mFAButton != null) {
            mFAButton.setRippleColor(ColorUtil.getComplimentColor(getResources().getColor(R.color.green_app)));
            mFAButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    getFragmentManager().beginTransaction()
                            .replace(R.id.container, AddSubjectFragment.newInstance())
                            .commit();

                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commit();
    }
}
