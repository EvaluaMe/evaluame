package com.garciparedes.evaluame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.garciparedes.evaluame.R;
import com.garciparedes.evaluame.fragments.BaseFragment;
import com.garciparedes.evaluame.fragments.HomeFragment;
import com.garciparedes.evaluame.fragments.subject.SubjectListFragment;
import com.garciparedes.evaluame.items.Subject;
import com.garciparedes.evaluame.provider.ListDB;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mikepenz.aboutlibraries.Libs;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.util.Colors;
import com.parse.ParseAnalytics;
import com.parse.ParseUser;
import com.parse.ui.ParseLoginBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
        implements BaseFragment.FragmentCallbacks {

    private static final int LOGIN_REQUEST = 0;

    private static final String SAVED_FRAGMENT = "saved_fragment";

    private BaseFragment mCurrentFragment;

    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    private ParseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        currentUser = ParseUser.getCurrentUser();
        if (currentUser != null) {
            showProfileLoggedIn();
        } else {
            showProfileLoggedOut();
        }

        if (ListDB.getMasterList() == null) {
            getData();
        }

        ParseAnalytics.trackAppOpenedInBackground(getIntent());


        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);

        if (mNavigationView != null) {
            setupDrawerContent(mNavigationView);
        }


        if (savedInstanceState != null) {
            //Restore the fragment's instance
            onCurrentFragmentChanged( (BaseFragment) getSupportFragmentManager().getFragment(
                    savedInstanceState, SAVED_FRAGMENT));
        } else {
            onCurrentFragmentChanged(HomeFragment.newInstance());
        }

        changeFragment();
    }

    /**
     * Shows the profile of the given user.
     */
    private void showProfileLoggedIn() {
        Toast.makeText(this, currentUser.getUsername(), Toast.LENGTH_LONG).show();
        //ParseUser.logOut();

    }

    /**
     * Show a message asking the user to log in, toggle login/logout button text.
     */
    private void showProfileLoggedOut() {
        // Send user to LoginSignupActivity.class
        ParseLoginBuilder builder = new ParseLoginBuilder(MainActivity.this);
        builder.setAppLogo(R.drawable.ic_launcher);

        startActivityForResult(builder.build(), LOGIN_REQUEST);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, SAVED_FRAGMENT, mCurrentFragment);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        onNavigationDrawerItemSelected(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                }
        );
    }


    /**
     *
     * @param menuItem
     */
    public void onNavigationDrawerItemSelected(MenuItem menuItem) {
        // update the main content by replacing fragments

        switch (menuItem.getItemId()){

            case R.id.nav_home:
                onCurrentFragmentChanged(HomeFragment.newInstance());
                break;

            case R.id.nav_subjects:
                onCurrentFragmentChanged(SubjectListFragment.newInstance());
                break;

            case R.id.nav_settings:
                startSettingsActivity();
                break;

            case R.id.nav_about:
                startAboutActivity();
                break;

            default:
                break;
        }

        changeFragment();
    }


    /**
     * Start Settings Activity.
     */
    public void startSettingsActivity(){
        startActivity(new Intent(this, SettingsActivity.class));
    }


    /**
     * Start About Activity.
     */
    public void startAboutActivity(){
        new LibsBuilder()
                //Pass the fields of your application to the lib so it can find all external lib information
                .withFields(R.string.class.getFields())
                        //provide a style (optional) (LIGHT, DARK, LIGHT_DARK_TOOLBAR)
                .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
                .withActivityColor(new Colors(getResources().getColor(R.color.green_app), getResources().getColor(R.color.green_app_dark)))
                .withAboutIconShown(true)
                .withAboutVersionShown(true)
                .withAboutDescription(getResources().getString(R.string.app_description))

                        //start the activity
                .start(this);
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


    /**
     * This method load stored data to the app.
     */
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


    /**
     * This method call it backPressed method.
     */
    @Override
    public void onBackPressed() {
        mCurrentFragment.onBackPressed();
    }


    /**
     * Getter of Current Fragment.
     *
     * @return mCurrentFragment
     */
    public BaseFragment getCurrentFragment() {
        return mCurrentFragment;
    }


    /**
     * Method who change fragment to current fragment.
     */
    public void changeFragment(){
        changeFragment(getCurrentFragment());
    }


    /**
     * Method who change fragment.
     * 
     * @param fragment
     */
    public void changeFragment(BaseFragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
