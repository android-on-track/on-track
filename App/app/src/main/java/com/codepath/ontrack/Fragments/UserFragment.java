package com.codepath.ontrack.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.ontrack.LoginActivity;
import com.codepath.ontrack.Parse.UserProfile;
import com.codepath.ontrack.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class UserFragment extends Fragment {
    private Button logoutBtn;
    //NEW MAY 8 SB
    private TextView tv_FirstName;
    private TextView tv_LastName;
    private TextView tv_title;
    private TextView tv_about;
    private TextView tv_points;

    private ImageView iv_profilePic;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //NEW MAY 8 SB
        tv_FirstName = view.findViewById(R.id.tv_FirstName);
        tv_LastName = view.findViewById(R.id.tv_LastName);
        tv_title = view.findViewById(R.id.tv_title);
        tv_about = view.findViewById(R.id.tv_about);
        tv_points = view.findViewById(R.id.tv_points);
        iv_profilePic = view.findViewById(R.id.iv_profilePic);

        queryUserProfile();
        //END NEW
    }

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
                tv_FirstName.setText(userP.getFirstName());
                tv_LastName.setText(userP.getLastName());
                tv_about.setText(userP.getAbout());
                tv_title.setText(userP.getTitle());
                tv_points.setText(Integer.toString(userP.getTaskCompleted()));
               // Glide.with(UserFragment.this).load(userP.getProfilePic()).into(iv_profilePic);
                Log.d("XPARSE", userP.getProfilePic().toString());
            }
        });
    }

    private void goLoginActivity() {
        Intent i = new Intent(this.getActivity(), LoginActivity.class);
        startActivity(i);
    }
}
