package com.codepath.ontrack;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.ontrack.Parse.Lap;

import java.util.List;

public class BacklogAdapter extends RecyclerView.Adapter<BacklogAdapter.ViewHolder>{
    private Context context;
    private List<Lap> lap;
    private String fragment_pick;

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
                            dialog_add_baton.dismiss();   // closes the dialog after the button press
                        }
                    });
                    dialog_add_baton.show();
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
}
