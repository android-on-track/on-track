package com.codepath.ontrack.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.R;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProgressFragment extends Fragment {
    private TextView tvTotalLaps;
    private TextView tvTotLapsComp;
    private TextView tvProgressPercentage;
    private CircularProgressBar progressCircleX;


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

        queryBackLog();
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
                BackLog backLog = ParsedItems.get(0);
                float task = backLog.getNumofTask(), comp = backLog.getNumofCompleted();
                tvTotalLaps.setText(Integer.toString(backLog.getNumofTask()));
                tvTotLapsComp.setText(Integer.toString(backLog.getNumofCompleted()));
                tvProgressPercentage.setText(Float.toString((comp / task)* 100) + "%");
                progressCircleX.setProgress(comp);
                progressCircleX.setProgressMax(task);
                int animationDuration = 3000; // 2500ms = 2,5s
                progressCircleX.setProgressWithAnimation(comp, animationDuration); // Default duration = 1500ms
            }
        });
    }
}
