package com.codepath.ontrack;

import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.Baton;
import com.codepath.ontrack.Parse.Lap;
import com.parse.ParseException;
import com.parse.SaveCallback;

import java.util.List;

public class BacklogAdapter extends RecyclerView.Adapter<BacklogAdapter.ViewHolder>{
    private Context context;
    private List<Lap> lap;
    private String fragment_pick;

    private FloatingActionButton fab_profile;

    //Comp MAY23 SB
    private BackLog currentBacklog;
    private Button btn_Low;
    private Button btn_Mid;
    private Button btn_High;

    //Increament
    private TextView tv_weight;
    private Button btn_increment;
    private Button btn_decrement;
    private TextView et_checkpoint_description;


    //Values   MAY23 SB
    private String priority;
    private int lapValue;

    public BacklogAdapter(Context context, List<Lap> laps, String fragment_pick){
        this.context = context;
        this.lap = laps;
        this.fragment_pick = fragment_pick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_lap, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Lap lapx = lap.get(position);
        viewHolder.bind(lapx);
    }

    public void clear() {
        lap.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lap.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPriority;
        private TextView tvLapDescription;
        private TextView tvLapPoints;
        private TextView tvPointsTitle;
        private TextView tvLapCount;
        private TextView tvFilecount;
        private Button btn_add_baton;
        private ImageView btn_close_baton;
        private ImageView ivPriorityColor;
        private ImageView ivLapIcon;
        private TextView et_baton_description;
        private ImageView ivFileIcon;
        private Dialog dialog_add_baton;
        private FloatingActionButton fab_add_baton;
        private FloatingActionButton btnProfileiBacklog;



        public ViewHolder(View itemView){
            super(itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvLapDescription = itemView.findViewById(R.id.tvLapDescription);
            tvLapPoints = itemView.findViewById(R.id.tvLapPoints);
            tvPointsTitle = itemView.findViewById(R.id.tvPointsTitle);
            tvLapCount = itemView.findViewById(R.id.tvLapCount);
            tvFilecount = itemView.findViewById(R.id.tvFilecount);
            btnProfileiBacklog = itemView.findViewById(R.id.fab_profile_backlog);
            ivPriorityColor = itemView.findViewById(R.id.ivPriorityColor);
            ivLapIcon = itemView.findViewById(R.id.ivLapIcon);
            ivFileIcon = itemView.findViewById(R.id.ivFileIcon);
            et_baton_description = itemView.findViewById(R.id.et_baton_description);

            fab_add_baton = itemView.findViewById(R.id.fab_add_baton);
            fab_add_baton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_add_baton = new Dialog(context);
                    dialog_add_baton.setContentView(R.layout.add_baton);
                    dialog_add_baton.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    btn_add_baton = dialog_add_baton.findViewById(R.id.btn_newBaton);
                    btn_close_baton = dialog_add_baton.findViewById(R.id.btn_close_baton);

                    //NEW MAY 23 SB
                    priority = "";
                    lapValue = 1;

                    btn_Low = dialog_add_baton.findViewById(R.id.btn_Low);
                    btn_Mid = dialog_add_baton.findViewById(R.id.btn_Mid);
                    btn_High = dialog_add_baton.findViewById(R.id.btn_High);

                    tv_weight = dialog_add_baton.findViewById(R.id.tv_weight);
                    btn_increment = dialog_add_baton.findViewById(R.id.btn_increment);
                    btn_decrement = dialog_add_baton.findViewById(R.id.btn_decrement);

                    et_checkpoint_description = dialog_add_baton.findViewById(R.id.et_baton_description);

                    //PRIORITY BUTTONS
                    btn_Low.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            priority = "Low";
                            btn_Low.setBackground(v.getResources().getDrawable(R.drawable.gradiantlow));
                            btn_Mid.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                            btn_High.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                        }
                    });

                    btn_Mid.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            priority = "Mid";
                            btn_Low.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                            btn_Mid.setBackground(v.getResources().getDrawable(R.drawable.gradiantlow));
                            btn_High.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                        }
                    });

                    btn_High.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            priority = "High";
                            btn_Low.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                            btn_Mid.setBackground(v.getResources().getDrawable(R.drawable.gradianthigh));
                            btn_High.setBackground(v.getResources().getDrawable(R.drawable.gradiantlow));
                        }
                    });


                    btn_close_baton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_add_baton.dismiss();
                        }
                    });

                    //PRIORITY BUTTONS


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


                    btn_close_baton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_add_baton.dismiss();
                        }
                    });


                    btn_add_baton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // parse the baton
                            // et_baton_description
                            //
                            //
                            String des = et_checkpoint_description.getText().toString();
                            saveBaton(des, lapValue, priority);
                            dialog_add_baton.dismiss();   // closes the dialog after the button press
                        }
                    });
                    dialog_add_baton.show();
                    //END MAY23
                }
            });

        }

        public void bind(Lap lapx){
            tvPriority.setText("Priority " + lapx.getPriority());
            tvLapDescription.setText(lapx.getDescription());
            tvLapPoints.setText(Integer.toString(lapx.getTotalPoints()));
            tvLapCount.setText(Integer.toString(lapx.getBatonCount()));
            tvFilecount.setText(Integer.toString(lapx.getFileCount()));



            switch (lapx.getPriority()) {
                case "Mid":
                    Glide.with(context).load(R.drawable.gradiantmed).into(ivPriorityColor);
                    break;
                case "High":
                    Glide.with(context).load(R.drawable.gradianthigh).into(ivPriorityColor);
                    break;
                default:
                    Glide.with(context).load(R.drawable.gradiantlow).into(ivPriorityColor);
                    break;
            }


           // ParseFile image = lapx.getImage();
           // if(image != null) {
          //      Glide.with(context).load(image.getUrl()).into(ivImage);
          //  }

        }
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
                    Toast.makeText(context, "NOTHINGHAPPEND", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    return;
                }
                Toast.makeText(context, "New BATON Created!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
