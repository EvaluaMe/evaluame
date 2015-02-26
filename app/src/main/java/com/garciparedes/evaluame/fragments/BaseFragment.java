package com.garciparedes.evaluame.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;

import com.garciparedes.evaluame.R;

/**
 * Created by garciparedes on 20/2/15.
 */
public abstract class BaseFragment extends Fragment {

    private FragmentCallbacks mCallbacks;

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
}
