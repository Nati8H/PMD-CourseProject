package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.DBManager;
import com.myproject.myapplication.R;
import com.myproject.myapplication.utils.SessionManager;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText password;

    DBManager db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        try {
            sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        password = findViewById(R.id.editPassword);
    }

    public void changePassword(View view) {
        db = new DBManager();
        String currentPassword = password.getText().toString();

        if (!currentPassword.equals(sessionManager.getPassword()) && !currentPassword.isEmpty()) {
            db.changePassword(sessionManager.getUserId(), currentPassword);
            sessionManager.changePassword(currentPassword);
        }

        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void returnToSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}