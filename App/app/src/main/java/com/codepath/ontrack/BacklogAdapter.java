package com.codepath.ontrack;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.ontrack.Fragments.BacklogFragment;
import com.codepath.ontrack.Fragments.LapFragment;
import com.codepath.ontrack.Fragments.ProgressFragment;
import com.codepath.ontrack.Fragments.UserFragment;
import com.codepath.ontrack.Parse.Lap;
import com.parse.ParseFile;

import android.support.v7.widget.RecyclerView;

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

        private ImageView ivPriorityColor;
        private ImageView ivLapIcon;
        private ImageView ivFileIcon;

        private FloatingActionButton btnAddLap;
        private FloatingActionButton btnProfileiBacklog;



        public ViewHolder(View itemView){
            super(itemView);
            tvPriority = itemView.findViewById(R.id.tvPriority);
            tvLapDescription = itemView.findViewById(R.id.tvLapDescription);
            tvLapPoints = itemView.findViewById(R.id.tvLapPoints);
            tvPointsTitle = itemView.findViewById(R.id.tvPointsTitle);
            tvLapCount = itemView.findViewById(R.id.tvLapCount);
            tvFilecount = itemView.findViewById(R.id.tvFilecount);

            ivPriorityColor = itemView.findViewById(R.id.ivPriorityColor);
            ivLapIcon = itemView.findViewById(R.id.ivLapIcon);
            ivFileIcon = itemView.findViewById(R.id.ivFileIcon);

            btnAddLap = itemView.findViewById(R.id.btnAddLap);
            btnProfileiBacklog = itemView.findViewById(R.id.btnProfileiBacklog);
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
