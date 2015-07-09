package com.garciparedes.evaluame.fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.utils.*;
import com.garciparedes.evaluame.activities.MainActivity;

/**
 * Created by garciparedes on 20/2/15.
 */
public abstract class BaseFragment extends Fragment {

    private FragmentCallbacks mCallbacks;
    public Toolbar toolbar;
    public CollapsingToolbarLayout collapsingToolbar;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCallbacks.onCurrentFragmentChanged(this);
    }
    public abstract void onBackPressed();


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.global, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface FragmentCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onCurrentFragmentChanged(BaseFragment baseFragment);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (FragmentCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement FragmentCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    private void setupToolbar(View view){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);

    }



    public void customizeActionBar(int color, String title , String subTitle){

        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();

        if (toolbar != null) {
            toolbar.setBackgroundColor(color);


            if (title != null) {
                //toolbar.setTitle(title);
                actionBar.setTitle(title);
            }

            if (subTitle != null) {
                toolbar.setSubtitle(subTitle);
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getActivity().getWindow().setStatusBarColor(ColorUtil.getDarkness(color));
            }
        }

        if(collapsingToolbar != null){
            collapsingToolbar.setBackgroundColor(color);
            collapsingToolbar.setDrawingCacheBackgroundColor(color);
            collapsingToolbar.setTitle(title);
            collapsingToolbar.setExpandedTitleTextAppearance(R.style.ToolbarExpandedTitle);
            //collapsingToolbar.setStatusBarScrimColor(color);
            collapsingToolbar.setContentScrimColor(color);

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((MainActivity) getActivity()).restoreActionBar();
    }
}
