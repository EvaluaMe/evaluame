package com.garciparedes.evaluame.application;

import android.app.Application;
import android.content.res.Configuration;

import com.garciparedes.evaluame.R;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseTwitterUtils;

/**
 * Created by garciparedes on 15/7/15.
 */
public class EvaluaMe extends Application {

    private static final String APPLICATION_ID = "Irm18S3p9pCtusyGlCHE7212JPPCkPu9A8Ev9P5r";
    private static final String CLIENT_KEY = "BJ5Gi7S83xurjLe3CS1U4ogW8BixKRb39TL2aqkL";
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
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        ParseFacebookUtils.initialize(this);

        // Optional - If you don't want to allow Twitter login, you can
        // remove this line (and other related ParseTwitterUtils calls)
        ParseTwitterUtils.initialize("6tef42byALWJl3LKhb13xcpWO",
                "Q4kYOhMQ3PgfdFelDCC5D3Lq2hylm7lRpPGoQvpk43iNB3nsnF");

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