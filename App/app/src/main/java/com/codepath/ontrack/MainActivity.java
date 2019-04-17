package com.codepath.ontrack;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.UserProfile;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //tv1 = findViewById(R.id.tv1);

        //       queryBaton();
        //       queryBackLog();
        //      queryUserProfile();

    }


    //Query Data from the UserProfile
    private void queryUserProfile() {
        ParseQuery<UserProfile> parseQuery = new ParseQuery<>(UserProfile.class);
        parseQuery.include(UserProfile.KEY_USER);
        parseQuery.whereEqualTo(UserProfile.KEY_USER, ParseUser.getCurrentUser());
        parseQuery.findInBackground(new FindCallback<UserProfile>() {
            @Override
            public void done(List<UserProfile> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "USERPROFILE");
                UserProfile userP = ParsedItems.get(0);
                Log.d("XPARSE", userP.getFirstName());
                Log.d("XPARSE", userP.getLastName());
                Log.d("XPARSE", userP.getTitle());
                Log.d("XPARSE", Integer.toString(userP.getPoints()));
                Log.d("XPARSE", Integer.toString(userP.getTaskCompleted()));
                Log.d("XPARSE", userP.getAbout());
                Log.d("XPARSE", userP.getProfilePic().toString());
                Log.d("XPARSE", userP.getUser().toString());
            }
        });
    }

    //Query Data from the BackLog
    private void queryBackLog() {
        ParseQuery<BackLog> parseQuery = new ParseQuery<>(BackLog.class);
        parseQuery.include(BackLog.KEY_USER);
        parseQuery.whereEqualTo(BackLog.KEY_USER, ParseUser.getCurrentUser());
        parseQuery.findInBackground(new FindCallback<BackLog>() {
            @Override
            public void done(List<BackLog> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "BackLog");
                BackLog userP = ParsedItems.get(0);
                Log.d("XPARSE", userP.getUser().toString());
                Log.d("XPARSE", userP.getName());
                Log.d("XPARSE", userP.getCompleted().toString());
                Log.d("XPARSE", Integer.toString(userP.getNumofTask()));
                Log.d("XPARSE", Integer.toString(userP.getNumofCompleted()));
            }
        });
    }

    //Query Data from the Baton
    private void queryBaton() {
        ParseQuery<Baton> parseQuery = new ParseQuery<>(Baton.class);
        parseQuery.include(Baton.KEY_USER);
        parseQuery.whereEqualTo(Baton.KEY_USER, ParseUser.getCurrentUser());
        parseQuery.findInBackground(new FindCallback<Baton>() {
            @Override
            public void done(List<Baton> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "Baton");
                Baton userP = ParsedItems.get(0);
                Log.d("XPARSE", userP.getUser().toString());
                Log.d("XPARSE", userP.getBackLogID().toString());
                Log.d("XPARSE", Integer.toString(userP.getPoints()));
                Log.d("XPARSE", userP.getPic().toString());
                Log.d("XPARSE", userP.getDescription());
                Log.d("XPARSE", userP.getName());
                Log.d("XPARSE", Integer.toString(userP.getNumofDays()));
                Log.d("XPARSE", userP.getCompleted().toString());

            }
        });
    }


}
