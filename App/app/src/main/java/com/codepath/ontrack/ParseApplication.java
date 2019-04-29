package com.codepath.ontrack;

import android.app.Application;

import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.Lap;
import com.codepath.ontrack.Parse.LapFile;
import com.codepath.ontrack.Parse.UserProfile;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Register Classes so that it can be parsed or else you will get a "couldn't cast error"
        ParseObject.registerSubclass(UserProfile.class);
        ParseObject.registerSubclass(BackLog.class);
        ParseObject.registerSubclass(Baton.class);
        ParseObject.registerSubclass(Lap.class);
        ParseObject.registerSubclass(LapFile.class);


        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("get-ontrack") // should correspond to APP_ID env variable
                .clientKey("CeesMarkSteven")  // set explicitly unless clientKey is explicitly configured on Parse server
                .server("https://get-ontrack.herokuapp.com/parse").build());
    }
}
