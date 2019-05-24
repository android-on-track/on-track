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
import android.widget.Toast;

import com.codepath.ontrack.BacklogAdapter;
import com.codepath.ontrack.LapAdapter;
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

public class LapFragment extends Fragment {

    private RecyclerView rvLaps;
    private FloatingActionButton fab_add_checkpoint;
    private Dialog dialog_add_checkpoint;
    private Button btn_add_checkpoint;
    private ImageView btn_close_checkpoint;
    protected LapAdapter adapter;
    private TextView et_checkpoint_description;
    protected List<Baton> mLaps;
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

    //MAY 22 SB
    private ImageView ivPriorityColor;

    private SwipeRefreshLayout swipeContainer;
    ///////NEWWWWWWW
    private Dialog compBaton;
    private Button btnYes;
    private Button btnNo;
    private FloatingActionButton fbp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        swipeContainer = view.findViewById(R.id.SwipeContainer);
        fab_add_checkpoint = view.findViewById(R.id.fab_add_checkpoint);
        et_checkpoint_description = view.findViewById(R.id.et_checkpoint_description);
        rvLaps = view.findViewById(R.id.rvLaps);
        tv_weight = view.findViewById(R.id.tv_weight);
        dialog_add_checkpoint = new Dialog(getContext());


        mLaps = new ArrayList<>();
        adapter = new LapAdapter(getContext(), mLaps, "PostsFragment");
        rvLaps.setAdapter(adapter);
        rvLaps.setLayoutManager(new LinearLayoutManager(getContext()));
        queryBaton();
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                queryBaton();
            }
        });


        fab_add_checkpoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_add_checkpoint.setContentView(R.layout.add_checkpoint);
                dialog_add_checkpoint.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                btn_add_checkpoint = dialog_add_checkpoint.findViewById(R.id.btn_new_checkpoint);
                btn_close_checkpoint = dialog_add_checkpoint.findViewById(R.id.btn_close_checkpoint);

                ///////////////////////////////////////////////////////////ADD LAP
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
                        saveBaton(des, lapValue, priority);
                        dialog_add_checkpoint.dismiss();   // closes the dialog after the button press
                        adapter.clear();
                        queryBaton();
                    }
                });
                dialog_add_checkpoint.show();
            }
        });


    }

    //////////////////////////////////////////////////////////////////////////////////BACKEND SERVE
    //Query Data from Lap
    private void queryBaton() {
        ParseQuery<Baton> parseQuery = new ParseQuery<>(Baton.class);
        parseQuery.include(Baton.KEY_LAP);
        parseQuery.findInBackground(new FindCallback<Baton>() {
            @Override
            public void done(List<Baton> ParsedItems, ParseException e) {
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
                    Baton userP = ParsedItems.get(i);
                }

            }

        });
        swipeContainer.setRefreshing(false);
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
