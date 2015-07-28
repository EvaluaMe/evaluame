package com.garciparedes.evaluame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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

    private BaseFragment currentFragment;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    private ParseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }


        if (savedInstanceState != null) {
            //Restore the fragment's instance
            changeFragment( (BaseFragment) getSupportFragmentManager().getFragment(
                    savedInstanceState, SAVED_FRAGMENT));
        } else {
            changeFragment(HomeFragment.newInstance());
        }
    }

    /**
     * Shows the profile of the given user.
     */
    private void showProfileLoggedIn() {;
        
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
        getSupportFragmentManager().putFragment(outState, SAVED_FRAGMENT, currentFragment);
    }


    /**
     * Setup Navigation Drawer view.
     *
     * @param navigationView navigationView
     */
    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        onNavigationDrawerItemSelected(menuItem);
                        drawerLayout.closeDrawers();
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
                changeFragment(HomeFragment.newInstance());
                break;

            case R.id.nav_subjects:
                changeFragment(SubjectListFragment.newInstance());
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
    }


    /**
     * Start Settings Activity.
     */
    public void startSettingsActivity() {
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
                .withAboutAppName(getResources().getString(R.string.app_name))
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
        setCurrentFragment(baseFragment);
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
        if (getDrawerLayout().isDrawerOpen(getNavigationView()))
            getDrawerLayout().closeDrawers();
        else {
            getCurrentFragment().onBackPressed();
        }
    }


    /**
     * Setter of Current Fragment.
     *
     * @param currentFragment currentFragment
     */
    public void setCurrentFragment(BaseFragment currentFragment) {
        this.currentFragment = currentFragment;
    }

    /**
     * Getter of Current Fragment.
     *
     * @return currentFragment
     */
    public BaseFragment getCurrentFragment() {
        return currentFragment;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public NavigationView getNavigationView() {
        return navigationView;
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
