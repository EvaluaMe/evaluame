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
import com.garciparedes.evaluame.activities.MainActivity;
import com.garciparedes.evaluame.utils.ColorUtil;

/**
 * Created by garciparedes on 20/2/15.
 */
public abstract class BaseFragment extends Fragment {

    private FragmentCallbacks mCallbacks;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;


    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCallbacks.onCurrentFragmentChanged(this);
    }


    /**
     * Method who implements back action.
     */
    public abstract void onBackPressed();


    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public interface FragmentCallbacks {
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


    /**
     * Method who initialize toolbar.
     * @param view
     */
    public void initToolbar(View view){
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        getMainActivity().setSupportActionBar(toolbar);

        getMainActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getMainActivity().getSupportActionBar().setHomeButtonEnabled(true);
        collapsingToolbar =
                (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
    }


    /**
     * Method who customize toolbar.
     *
     * @param color
     * @param title
     * @param subTitle
     */
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


    /**
     * Getter of MainActivity.
     * @return
     */
    private MainActivity getMainActivity(){
        return (MainActivity) getActivity();
    }


    /**
     * Method who change fragment.
     *
     * @param fragment
     */
    public void changeFragment(BaseFragment fragment){
        getMainActivity().changeFragment(fragment);
    }
}
