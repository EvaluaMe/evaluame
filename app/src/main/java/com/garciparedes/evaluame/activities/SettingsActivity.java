package com.garciparedes.evaluame.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.garciparedes.evaluame.R;
import com.parse.ParseUser;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getFragmentManager().beginTransaction().replace(R.id.container, new MyPreferenceFragment()).commit();
    }


    /**
     * It's method is called to LogOut
     */
    public void logOut(){
        ParseUser.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public static class MyPreferenceFragment extends PreferenceFragment {

        private ParseUser currentUser;

        private SharedPreferences.Editor editor;
        private SharedPreferences sharedPreferences;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            currentUser = ParseUser.getCurrentUser();

            sharedPreferences =PreferenceManager.getDefaultSharedPreferences(getActivity());
            editor = sharedPreferences.edit();

            editor.putString("email", currentUser.getEmail());
            editor.putString("username", currentUser.getUsername());
            editor.putString("name", currentUser.get("name").toString());

            editor.apply();

            addPreferencesFromResource(R.xml.settings);

            Preference preferenceUsername = findPreference("username");
            preferenceUsername.setSummary(sharedPreferences.getString("username", ""));

            Preference preferenceEmail = findPreference("email");
            preferenceEmail.setSummary(sharedPreferences.getString("email", ""));

            Preference preferenceName = findPreference("name");
            preferenceName.setSummary(sharedPreferences.getString("name", ""));

        }

        public SettingsActivity getSettingActivity(){
            return  (SettingsActivity) getActivity();
        }



        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            switch (preference.getTitleRes()){

                case (R.string.logout):
                    logOut();
                    return true;

            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);

        }

        public void logOut(){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                    getActivity());

            // set title
            alertDialogBuilder.setTitle(getString(R.string.logout));

            // set dialog message
            alertDialogBuilder
                    .setMessage(getString(R.string.log_out_confirmation))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, close
                            // current activity
                            getSettingActivity().logOut();
                        }
                    })
                    .setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // if this button is clicked, just close
                            // the dialog box and do nothing
                            dialog.cancel();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
    }
}