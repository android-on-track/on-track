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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.codepath.ontrack.Parse.Baton;

import java.util.List;

public class LapAdapter extends RecyclerView.Adapter<LapAdapter.ViewHolder>{
    private Context context;
    private List<Baton> lap;
    private String fragment_pick;

    ///////NEWWWWWWW
    private Dialog compBaton;



    public LapAdapter(Context context, List<Baton> laps, String fragment_pick){
        this.context = context;
        this.lap = laps;
        this.fragment_pick = fragment_pick;
    }

    @NonNull
    @Override
    public LapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.item_baton, parent, false);
        return new LapAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LapAdapter.ViewHolder viewHolder, int position) {
        Baton lapx = lap.get(position);
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
        private Button btnYes;
        private Button btnNo;
        private FloatingActionButton fbp;
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

            final View battt = itemView;

            //NEWWWW
            fbp = itemView.findViewById(R.id.fab_profile_backlog);
            compBaton = new Dialog(context);
            fbp.hide();
            fab_add_baton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    compBaton.setContentView(R.layout.complete_baton);
                    compBaton.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    btnYes = compBaton.findViewById(R.id.btn_yes);
                    btnNo = compBaton.findViewById(R.id.btn_no);


                    btnYes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // parse the checkpoint
                            // et_checkpoint_description
                            // tv_weight
                            //
                            Toast.makeText(context, "Congrats Baton Completed", Toast.LENGTH_SHORT).show();

                            battt.setVisibility(battt.GONE);
                            compBaton.dismiss();   // closes the dialog after the button press
                        }
                    });

                    btnNo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // parse the checkpoint
                            // et_checkpoint_description
                            // tv_weight
                            //
                            compBaton.dismiss();   // closes the dialog after the button press
                        }
                    });

                    compBaton.show();
                }
            });

        }

        public void bind(Baton lapx){
            tvPriority.setText("Priority " + lapx.getPriority());
            tvLapDescription.setText(lapx.getDescription());
            tvLapPoints.setText(Integer.toString(lapx.getPoints()));


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
