package com.codepath.ontrack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.request.RequestOptions;
import com.codepath.ontrack.Parse.BackLog;
import com.codepath.ontrack.Parse.UserProfile;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.io.File;
import java.nio.file.attribute.UserPrincipal;

public class SignupActivity extends AppCompatActivity {
    private Button btnSignup;
    private EditText etSignUsername;
    private EditText etSignPassword;
    private EditText etSignEmail;
    private ParseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignup = findViewById(R.id.btnSignup);

        etSignUsername = findViewById(R.id.etSignUsername);
        etSignPassword = findViewById(R.id.etSignPassword);
        etSignEmail = findViewById(R.id.etSignEmail);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String username = etSignUsername.getText().toString();
            String pass = etSignPassword.getText().toString();
            String email = etSignEmail.getText().toString();
            signup(username,pass,email);
            currentUser = ParseUser.getCurrentUser();
            creatUserProfile();
            createBackLog();

            startLoginActivity();
            }
        });
    }

    private void signup(String username, String pass, String email) {
        ParseUser user = new ParseUser();
        // Set core properties
        user.setUsername(username);
        user.setPassword(pass);
        user.setEmail(email);
        // Set custom properties
        // Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e("SignUpActivity", "Issue with Signup:   " + e.getMessage());
                    Toast.makeText(SignupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }


            }
        });
    }

    private void creatUserProfile(){
        UserProfile userProfile = new UserProfile();
       // userProfile.setUser(currentUser);
        userProfile.setFirstName("");
        userProfile.setLastName("");
        userProfile.setAbout("");
        userProfile.setTitle("");
        userProfile.setTaskCompleted(0);
        userProfile.setPoints(0);

        userProfile.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
            if(e != null) {
                Log.e("XPARSE", "SAVINNGGGERRROR");
                e.printStackTrace();
                return;
            }
            Toast.makeText(SignupActivity.this, "New Profile Created!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private  void createBackLog(){
        BackLog backLog = new BackLog();
       // backLog.setUser(currentUser);
        backLog.setNumofCompleted(0);
        backLog.setNumofTask(0);
        backLog.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
            if(e != null) {
                Log.e("XPARSE", "BACCKKLLOOGGgGERRROR");
                e.printStackTrace();
                return;
            }
            }
        });
    }

    private void startLoginActivity() {
        // Log.e("SignUpActivity", "Issue with Signup");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}
