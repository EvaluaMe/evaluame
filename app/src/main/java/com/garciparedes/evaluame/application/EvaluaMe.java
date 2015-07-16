package com.garciparedes.evaluame.application;

import android.app.Application;
import android.content.res.Configuration;

import com.parse.Parse;

/**
 * Created by garciparedes on 15/7/15.
 */
public class EvaluaMe extends Application {

    private static EvaluaMe singleton;

    public static EvaluaMe getInstance() {
        return singleton;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        // Enable Local Datastore.

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}