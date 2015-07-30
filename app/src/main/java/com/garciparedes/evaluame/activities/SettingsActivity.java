package com.garciparedes.evaluame.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

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

        private ParseUser parseUser;

        private SharedPreferences.Editor editor;
        private SharedPreferences sharedPreferences;

        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);

            parseUser = ParseUser.getCurrentUser();

            sharedPreferences =PreferenceManager.getDefaultSharedPreferences(getActivity());
            editor = sharedPreferences.edit();

            editor.putString("email", ParseUser.getCurrentUser().getEmail());
            editor.putString("username", ParseUser.getCurrentUser().getUsername());


            editor.apply();

            addPreferencesFromResource(R.xml.settings);

            EditTextPreference editTextPreferenceUsername = (EditTextPreference) findPreference("username");

            editTextPreferenceUsername.setSummary(sharedPreferences.getString("username", ""));
        }

        public SettingsActivity getSettingActivity(){
            return  (SettingsActivity) getActivity();
        }



        @Override
        public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
            switch (preference.getTitleRes()){
                case (R.string.receive_notifications):
                    Snackbar.make(getView(), "Hola", Snackbar.LENGTH_LONG).show();
                    return true;

                case (R.string.username):

                    String string = sharedPreferences.getString("username", "");
                    Snackbar.make(getView(), string, Snackbar.LENGTH_LONG).show();
                    preference.setSummary(string);
                    parseUser.setUsername(string);
                    parseUser.saveInBackground();

                    Toast.makeText(getActivity(),parseUser.getUsername(), Toast.LENGTH_LONG).show();

                    return true;

                case (R.string.email):

                    return true;

                case (R.string.logout):
                    Snackbar.make(getView(), "Hola", Snackbar.LENGTH_LONG).show();
                    getSettingActivity().logOut();
                    return true;

            }
            return super.onPreferenceTreeClick(preferenceScreen, preference);

        }
    }
}