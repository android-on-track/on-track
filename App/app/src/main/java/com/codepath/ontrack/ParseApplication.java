package com.codepath.ontrack;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("get-ontrack") // should correspond to APP_ID env variable
                .clientKey("CeesMarkSteven")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://get-ontrack.herokuapp.com/parse").build());
    }
}
