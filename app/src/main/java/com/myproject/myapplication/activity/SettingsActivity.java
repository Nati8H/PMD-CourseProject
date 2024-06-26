package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.R;
import com.myproject.myapplication.utils.SessionManager;

public class SettingsActivity extends AppCompatActivity {

    TextView userName;
    TextView email;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        try {
            sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        userName = findViewById(R.id.txtUsername);
        userName.setText(sessionManager.getUsername());

        email = findViewById(R.id.txtEmail);
        email.setText(sessionManager.getEmail());
    }

    public void returnToHomeButton(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void changeUsernameBtn(View view) {
        startActivity(new Intent(this, ChangeUsernameActivity.class));
    }

    public void changeEmailBtn(View view) {
        startActivity(new Intent(this, ChangeEmailActivity.class));
    }

    public void changePasswordBtn(View view) {
        startActivity(new Intent(this, ChangePasswordActivity.class));
    }

    public void logout(View view) {
        sessionManager.logoutUser();
        startActivity(new Intent(this, MainActivity.class));
    }
}