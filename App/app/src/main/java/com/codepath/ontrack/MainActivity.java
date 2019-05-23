package com.codepath.ontrack;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.codepath.ontrack.Fragments.BacklogFragment;
import com.codepath.ontrack.Fragments.LapFragment;
import com.codepath.ontrack.Fragments.ProgressFragment;
import com.codepath.ontrack.Fragments.UserFragment;
import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.Lap;
import com.codepath.ontrack.Parse.LapFile;
import com.codepath.ontrack.Parse.UserProfile;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private ParseObject BackLogObject;
    private String BackLogID = "";
    private String LapID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Backlog");
        setSupportActionBar(toolbar);



        //tv1 = findViewById(R.id.tv1);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null ) {
            toolbar.setTitle("Backlog");
            Fragment fragment = new BacklogFragment();
            fragmentManager.beginTransaction().replace(R.id.flContainer,fragment).commit();

        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.icon_backlog:
                        toolbar.setTitle("Backlog");
                        fragment = new BacklogFragment();
                        break;
                    case R.id.icon_lap:
                        toolbar.setTitle("Lap");
                        fragment = new LapFragment();
                        break;
                    case R.id.icon_progress:
                        toolbar.setTitle("Progress");
                        fragment = new ProgressFragment();
                        break;
                    case R.id.icon_user:
                        toolbar.setTitle("User Profile");
                        fragment = new UserFragment();
                        break;

                    default:
                        toolbar.setTitle("Backlog");
                        fragment = new BacklogFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer,fragment).commit();
                return true;
            }
        });


        //USED TO DEBUG DATABASE DATA
        //
        //queryUserProfile();
        //getBackLogID();
        //Log.d("XPARSE", BackLogID);
        //queryLap();
        //queryLapFile();
        //queryBaton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu, this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

/////////////////////////////////////////////////////////////////////////////////BACKEND SERVER
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
                Log.d("XPARSE", userP.getUser().toString());
                Log.d("XPARSE", userP.getFirstName());
                Log.d("XPARSE", userP.getLastName());
                Log.d("XPARSE", userP.getAbout());
                Log.d("XPARSE", userP.getTitle());
                Log.d("XPARSE", Integer.toString(userP.getTaskCompleted()));
                Log.d("XPARSE", Integer.toString(userP.getPoints()));
                Log.d("XPARSE", userP.getProfilePic().toString());
            }
        });
    }

    //Get BackLogID
    private void getBackLogID(){
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
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "BackLogID");
                BackLog userP = ParsedItems.get(0);

                BackLogID = userP.getObjectId();
                Log.d("XPARSE", BackLogID);
            }
        });
    }

    //Query Data from Lap
    private void queryLap() {
        ParseQuery<Lap> parseQuery = new ParseQuery<>(Lap.class);
        parseQuery.include(Lap.KEY_Backlog);
        parseQuery.whereContains(Lap.KEY_Backlog, BackLogID);
        parseQuery.findInBackground(new FindCallback<Lap>() {
            @Override
            public void done(List<Lap> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "Lap");
                for(int i = 0; i < ParsedItems.size(); i++) {
                    Lap userP = ParsedItems.get(i);
                    Log.d("XPARSE", userP.getBacklog().toString());
                    Log.d("XPARSE", userP.getName());
                    Log.d("XPARSE", userP.getDescription());
                    Log.d("XPARSE", userP.getPriority());
                    Log.d("XPARSE", Integer.toString(userP.getBatonCount()));
                    Log.d("XPARSE", Integer.toString(userP.getBatonCompleted()));
                    Log.d("XPARSE", Integer.toString(userP.getTotalPoints()));
                    Log.d("XPARSE", Integer.toString(userP.getFileCount()));
                    Log.d("XPARSE", userP.getDateSet().toString());
                    Log.d("XPARSE", userP.getCompleted().toString());
                    LapID = userP.getObjectId();
                }
            }
        });
    }

    //Query Data from Lap
    private void queryLapFile() {
        ParseQuery<LapFile> parseQuery = new ParseQuery<>(LapFile.class);
        parseQuery.include(LapFile.KEY_Lap);
        parseQuery.whereContains(LapFile.KEY_Lap, LapID);
        parseQuery.findInBackground(new FindCallback<LapFile>() {
            @Override
            public void done(List<LapFile> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "LapFile");
                    e.printStackTrace();
                    return;
                }

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "LapFile");
                for(int i = 0; i < ParsedItems.size(); i++) {
                    LapFile userP = ParsedItems.get(i);
                    Log.d("XPARSE", userP.getLap().toString());
                    Log.d("XPARSE", userP.getImage().toString());
                }
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
                Log.d("XPARSE", Integer.toString(userP.getNumofTask()));
                Log.d("XPARSE", Integer.toString(userP.getNumofCompleted()));
            }
        });
    }

    //Query Data from the Baton
    private void queryBaton() {
        ParseQuery<Baton> parseQuery = new ParseQuery<>(Baton.class);
        parseQuery.include(Baton.KEY_LAP);
        parseQuery.whereContains(Baton.KEY_LAP, LapID);
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
                Log.d("XPARSE", userP.getLap().toString());
                Log.d("XPARSE", userP.getName());
                Log.d("XPARSE", userP.getDescription());
                Log.d("XPARSE", userP.getPriority());
                Log.d("XPARSE", Integer.toString(userP.getPoints()));
                Log.d("XPARSE", userP.getCompleted().toString());

            }
        });
    }


}
