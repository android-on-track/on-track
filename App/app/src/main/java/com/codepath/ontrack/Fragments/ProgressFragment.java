package com.codepath.ontrack.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.Lap;
import com.codepath.ontrack.R;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ProgressFragment extends Fragment {
    private TextView tvTotalLaps;
    private TextView tvTotLapsComp;
    private TextView tvProgressPercentage;
    private CircularProgressBar progressCircleX;
    private Button btn_Calendar;
    private CalendarView calendarX;

    private Dialog openCalander;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTotalLaps = view.findViewById(R.id.tvTotalLaps);
        tvTotLapsComp = view.findViewById(R.id.tvTotLapsComp);
        tvProgressPercentage = view.findViewById(R.id.tvProgressPercentage);
        progressCircleX = view.findViewById(R.id.progressCircleX);
        btn_Calendar = view.findViewById(R.id.btn_Calendar);

        openCalander = new Dialog(getContext());

        btn_Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCalander.setContentView(R.layout.calender_lap);
                openCalander.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                calendarX = openCalander.findViewById(R.id.calendarX);

                String selectedDate = "26/05/2019";
                try {
                    calendarX.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                selectedDate = "28/05/2019";
                try {
                    calendarX.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }

                selectedDate = "30/05/2019";
                try {
                    calendarX.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(selectedDate).getTime(), true, true);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }


                openCalander.show();

            }
        });

        queryLap();
    }

    //////////////////////////////////////////////////////////////////////////////////BACKEND SERVE
    //Query Data from Lap
    //Query Data from Lap
    private void queryLap() {
        ParseQuery<Lap> parseQuery = new ParseQuery<>(Lap.class);
        parseQuery.include(Lap.KEY_Backlog);
        parseQuery.findInBackground(new FindCallback<Lap>() {
            @Override
            public void done(List<Lap> ParsedItems, ParseException e) {
                if(e != null) {
                    Log.e("XPARSE", "ERRROR");
                    e.printStackTrace();
                    return;
                }

                ;

                float task = ParsedItems.size(), comp = 4;
                tvTotalLaps.setText(Integer.toString(ParsedItems.size()));
                tvTotLapsComp.setText(Integer.toString(4));
                tvProgressPercentage.setText(Float.toString((comp / task)* 100) + "%");
                progressCircleX.setProgress(comp);
                progressCircleX.setProgressMax(task);
                int animationDuration = 5000;
                progressCircleX.setProgressWithAnimation(comp, animationDuration); // Default duration = 1500ms

            }

        });

    }

/*
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
                BackLog backLog = ParsedItems.get(0);
                float task = backLog.getNumofTask(), comp = backLog.getNumofCompleted();
                tvTotalLaps.setText(Integer.toString(backLog.getNumofTask()));
                tvTotLapsComp.setText(Integer.toString(backLog.getNumofCompleted()));
                tvProgressPercentage.setText(Float.toString((comp / task)* 100) + "%");
                progressCircleX.setProgress(comp);
                progressCircleX.setProgressMax(task);
                int animationDuration = 5000;
                progressCircleX.setProgressWithAnimation(comp, animationDuration); // Default duration = 1500ms
            }
        });
    }
*/

}
