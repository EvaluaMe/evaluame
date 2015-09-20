package com.garciparedes.evaluame.activities;

import android.content.Intent;

import com.garciparedes.evaluame.R;
import com.parse.ui.ParseLoginBuilder;
import com.parse.ui.ParseLoginDispatchActivity;

/**
 * Created by garciparedes on 30/7/15.
 */
public class LoginActivity extends ParseLoginDispatchActivity {

    @Override
    protected Class<?> getTargetClass() {
        return MainActivity.class;
    }

    @Override
    protected Intent getParseLoginIntent() {
        ParseLoginBuilder builder = new ParseLoginBuilder(this);
        builder.setAppLogo(R.drawable.logo);
        return builder.build();
    }
}
