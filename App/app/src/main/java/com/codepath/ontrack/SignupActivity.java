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

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {
    private Button btnSignup;
    private EditText etSignUsername;
    private EditText etSignPassword;
    private EditText etSignEmail;
    private TextView etLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        btnSignup = findViewById(R.id.btnSignup);
        etLogin = findViewById(R.id.etLogin);
        etSignUsername = findViewById(R.id.etSignUsername);
        etSignPassword = findViewById(R.id.etSignPassword);
        etSignEmail = findViewById(R.id.etSignEmail);

        etLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etSignUsername.getText().toString();
                String pass = etSignPassword.getText().toString();
                String email = etSignEmail.getText().toString();
                signup(username,pass,email);

            }
        });
    }

    private void signup(String username, String pass, String email) {
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername(username);
        user.setPassword(pass);
        user.setEmail(email);
        user.put("username",username);
        user.put("password",pass);
        user.put("email", email);

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
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                startLoginActivity();
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
