package com.garciparedes.evaluame.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.Util.Constant;
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


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, BaseFragment.FragmentCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private BaseFragment mCurrentFragment;
    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();

        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));


    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        //FragmentManager fragmentManager = getFragmentManager();

    switch (position){
        case -2:

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, AddSubjectFragment.newInstance())
                    .commit();
            break;
        case -1:
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, DefaultFragment.newInstance())
                    .commit();
            break;
        default:
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, SubjectFragment.newInstance(ListDB.get(position)))
                    .commit();
            break;
    }

    }

    public void onSectionAttached(int number) {
        mTitle = ListDB.get(number).getName();

    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    public void update() {
        mNavigationDrawerFragment.updateListView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
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

        /*
        new ShowcaseView.Builder(this)
                .setTarget(new ActionViewTarget(this, ActionViewTarget.Type.HOME))
                .setContentTitle("ShowcaseView")
                .setContentText("This is highlighting the Home button")
                .hideOnTouchOutside()
                .build();
        */

        ListDB.setMasterList(mList);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();

        } else {
            mCurrentFragment.onBackPressed();
        }
    }
}
