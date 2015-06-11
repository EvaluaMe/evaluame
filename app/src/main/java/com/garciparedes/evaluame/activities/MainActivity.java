package com.garciparedes.evaluame.activities;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    //private NavigationDrawerFragment mNavigationDrawerFragment;

    private BaseFragment mCurrentFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */

    private DrawerLayout mDrawerLayout;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ListDB.getMasterList() == null) {
            getData();
        }
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
        new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
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

        /*
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
        */
    }


    public void update() {

    }

    public Toolbar getToolbar(){
        if (mToolbar == null){
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
        }
        return mToolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

            mCurrentFragment.onBackPressed();

    }
}
