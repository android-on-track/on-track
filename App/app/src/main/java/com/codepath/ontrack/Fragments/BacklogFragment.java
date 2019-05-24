package com.codepath.ontrack.Fragments;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.ontrack.BacklogAdapter;
import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.Lap;
import com.codepath.ontrack.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BacklogFragment extends Fragment {

    private RecyclerView rvLaps;
    private FloatingActionButton fab_add_checkpoint;
    private Dialog dialog_add_checkpoint;
    private Button btn_add_checkpoint;
    private ImageView btn_close_checkpoint;
    protected BacklogAdapter adapter;
    private TextView et_checkpoint_description;
    protected List<Lap> mLaps;
    private TextView tv_title_check_point;
    private String BackLogID = "";

    private int lapValue;

    //NEW ADD CHECKPOINT SB MAY 8
    //Prio Btn
    private BackLog currentBacklog;
    private String priority;
    private Button btn_Low;
    private Button btn_Mid;
    private Button btn_High;

    //Increament
    private TextView tv_weight;
    private Button btn_increment;
    private Button btn_decrement;
    /////////// May 8

    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_backlog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        getBackLogID();
        swipeContainer = view.findViewById(R.id.SwipeContainer);
        fab_add_checkpoint = view.findViewById(R.id.fab_add_checkpoint);
        et_checkpoint_description = view.findViewById(R.id.et_checkpoint_description);


        rvLaps = view.findViewById(R.id.rvLaps);
        tv_weight = view.findViewById(R.id.tv_weight);
        dialog_add_checkpoint = new Dialog(getContext());
        mLaps = new ArrayList<>();
        adapter = new BacklogAdapter(getContext(), mLaps, "PostsFragment");
        rvLaps.setAdapter(adapter);
        rvLaps.setLayoutManager(new LinearLayoutManager(getContext()));

        queryLap();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryLap();
            }
        });


        fab_add_checkpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_checkpoint.setContentView(R.layout.add_checkpoint);
                dialog_add_checkpoint.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btn_add_checkpoint = dialog_add_checkpoint.findViewById(R.id.btn_new_checkpoint);
                btn_close_checkpoint = dialog_add_checkpoint.findViewById(R.id.btn_close_checkpoint);
                tv_title_check_point = dialog_add_checkpoint.findViewById(R.id.tv_title_check_point);
                //NEW MAY 8 SB
                priority = "";
                lapValue = 1;
                btn_Low = dialog_add_checkpoint.findViewById(R.id.btn_Low);
                btn_Mid = dialog_add_checkpoint.findViewById(R.id.btn_Mid);
                btn_High = dialog_add_checkpoint.findViewById(R.id.btn_High);

                tv_weight = dialog_add_checkpoint.findViewById(R.id.tv_weight);
                btn_increment = dialog_add_checkpoint.findViewById(R.id.btn_increment);
                btn_decrement = dialog_add_checkpoint.findViewById(R.id.btn_decrement);

                et_checkpoint_description = dialog_add_checkpoint.findViewById(R.id.et_checkpoint_description);


                //PRIORITY BUTTONS
                btn_Low.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        priority = "Low";
                        btn_Low.setBackground(getResources().getDrawable(R.drawable.gradiantlow));
                        btn_Mid.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                        btn_High.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                    }
                });

                btn_Mid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        priority = "Mid";
                        btn_Low.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                        btn_Mid.setBackground(getResources().getDrawable(R.drawable.gradiantlow));
                        btn_High.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                    }
                });

                btn_High.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        priority = "High";
                        btn_Low.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                        btn_Mid.setBackground(getResources().getDrawable(R.drawable.gradianthigh));
                        btn_High.setBackground(getResources().getDrawable(R.drawable.gradiantlow));
                    }
                });


                //Increament VALUE
                btn_increment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        lapValue++;
                        tv_weight.setText(Integer.toString(lapValue));
                    }
                });

                //Decreament VALUE
                btn_decrement.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(lapValue > 1)
                            lapValue--;
                        tv_weight.setText(Integer.toString(lapValue));
                    }
                });
                /////////// MAY 8 END SB


                btn_close_checkpoint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog_add_checkpoint.dismiss();
                    }
                });

                btn_add_checkpoint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // parse the checkpoint
                        // et_checkpoint_description
                        // tv_weight
                        //
                        String des = et_checkpoint_description.getText().toString();
                        saveCheckPoints(des, lapValue, priority);
                        dialog_add_checkpoint.dismiss();   // closes the dialog after the button press
                        adapter.clear();
                        queryLap();

                    }
                });
                dialog_add_checkpoint.show();
            }
        });

    }

    public void addNotification(){
        ////////////////////////////////Notifications
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
        mBuilder.setSmallIcon(R.id.icon_lap);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Hi, This is Android Notification Detail!");
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());

    }

////////////////////////////////////////////////////////////////////////////////////BACKEND SERVER
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
                currentBacklog =ParsedItems.get(0);
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

                mLaps.addAll(ParsedItems);
                adapter.notifyDataSetChanged();

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "Lap");
                for(int i = 0; i < ParsedItems.size(); i++) {
                    Lap userP = ParsedItems.get(i);
                }

            }

        });
        swipeContainer.setRefreshing(false);
    }
////////////////////////////////POST
    //Post NEW CheckPoints
    private void saveCheckPoints(String description, int points, String priority){
        Lap lap = new Lap();
        lap.setDesciption(description);
        lap.setTotalPoints(points);
        lap.setPriority(priority);
        lap.setName("NEW LAP");
        lap.setBatonCount(0);
        lap.setBatonCompleted(0);
        lap.setFileCount(0);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentTime = formatter.format(date);
        lap.setDateSet(date);
        lap.setBacklog(currentBacklog);
        lap.setCompleted(false);
        lap.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getContext(), "NOTHINGHAPPEND", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                Toast.makeText(getContext(), "New Baton Created!", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Post NEW BATONS
    private void saveBaton(String description, int points, String priority){
        Baton lap = new Baton();
        lap.setDescription(description);
        lap.setPoints(points);
        lap.setPriority(priority);
        lap.setName("NEW LAP");
        lap.setCompleted(false);
        lap.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    Toast.makeText(getContext(), "NOTHINGHAPPEND", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                Toast.makeText(getContext(), "New BATON Created!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
