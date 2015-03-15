package com.garciparedes.evaluame.activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.AddSubjectFragment;
import com.garciparedes.evaluame.fragments.BaseFragment;
import com.garciparedes.evaluame.fragments.DefaultFragment;
import com.garciparedes.evaluame.fragments.NavigationDrawerFragment;
import com.garciparedes.evaluame.fragments.SubjectFragment;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, BaseFragment.FragmentCallbacks {

    private static final String SAVED_FRAGMENT = "saved_fragment";
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private BaseFragment mCurrentFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ListDB.getMasterList() == null) {
            getData();
        }
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(mToolbar);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


        if (savedInstanceState != null) {
            //Restore the fragment's instance
            mCurrentFragment = (BaseFragment) getSupportFragmentManager().getFragment(
                    savedInstanceState, SAVED_FRAGMENT);
        } else {
            mCurrentFragment = DefaultFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,mCurrentFragment)
                .commit();
        //restoreActionBar();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, SAVED_FRAGMENT, mCurrentFragment);
    }



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        BaseFragment baseFragment;

        switch (position){
            case -2:
                baseFragment = AddSubjectFragment.newInstance();
                break;
            case -1:
                baseFragment = DefaultFragment.newInstance();
                break;
            default:
                baseFragment = SubjectFragment.newInstance(ListDB.get(position));
                break;
        }
        
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, baseFragment)
                .commit();

    }

    public void restoreActionBar() {

        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        }
        if (mToolbar != null){
            mToolbar.setBackgroundColor(getResources().getColor(R.color.green_app));

            mToolbar.setLayoutParams(
                    new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    )
            );
            mToolbar.setTitle(mTitle);
            mToolbar.setSubtitle(null);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.green_app_dark));
        }
    }


    public void update() {
        mNavigationDrawerFragment.updateListView();
    }

    public Toolbar getToolbar(){
        if (mToolbar == null){
            mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        }
        return mToolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.main, menu);
            //restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Called when an item in the navigation drawer is selected.
     *
     * @param baseFragment
     */
    @Override
    public void onCurrentFragmentChanged(BaseFragment baseFragment) {
        mCurrentFragment = baseFragment;
    }

    public void getData() {

        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(this.getApplicationContext());
        Gson gson = new Gson();
        String json = appSharedPrefs.getString("MasterList", "");
        Type type = new TypeToken<ArrayList<Subject>>() {
        }.getType();
        ArrayList<Subject> mList = gson.fromJson(json, type);
        if (mList == null) {
            mList = new ArrayList<Subject>();
        }

        ListDB.setMasterList(mList);
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
        } else {
            mCurrentFragment.onBackPressed();
        }
    }
}
