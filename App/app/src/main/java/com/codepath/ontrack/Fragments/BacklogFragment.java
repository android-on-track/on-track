package com.codepath.ontrack.Fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

import com.codepath.ontrack.BacklogAdapter;
import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Lap;
import com.codepath.ontrack.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class BacklogFragment extends Fragment {

    private RecyclerView rvLaps;
    private FloatingActionButton floatingActionButton;
    private Dialog dialog;
    private Button btn_addBaton;
    private ImageView btn_close;
    protected BacklogAdapter adapter;
    private TextView etDescrption;
    protected List<Lap> mLaps;
    private String BackLogID = "";

    private SwipeRefreshLayout swipeContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_backlog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getBackLogID();
        swipeContainer = view.findViewById(R.id.SwipeContainer);
        floatingActionButton = view.findViewById(R.id.fa_btn);
        etDescrption = view.findViewById(R.id.etDescription);
        rvLaps = view.findViewById(R.id.rvLaps);
        dialog = new Dialog(getContext());
        mLaps = new ArrayList<>();
        adapter = new BacklogAdapter(getContext(), mLaps, "PostsFragment");
        rvLaps.setAdapter(adapter);
        rvLaps.setLayoutManager(new LinearLayoutManager(getContext()));
        queryLap();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryLap();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.add_baton);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btn_addBaton = dialog.findViewById(R.id.btn_newBaton);
                btn_close = dialog.findViewById(R.id.btn_close);
                btn_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                btn_addBaton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // parse the baton
                        // etDescription
                        //
                        //
                        dialog.dismiss();   // closes the dialog after the button press
                    }
                });
                dialog.show();
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

                mLaps.addAll(ParsedItems);
                adapter.notifyDataSetChanged();

                //Quick Debug CHECK
                Log.d("XPARSE", Integer.toString(ParsedItems.size()) + "Lap");
                for(int i = 0; i < ParsedItems.size(); i++) {
                    Lap userP = ParsedItems.get(i);
                }
                swipeContainer.setRefreshing(false);
            }

        });
    }
}
