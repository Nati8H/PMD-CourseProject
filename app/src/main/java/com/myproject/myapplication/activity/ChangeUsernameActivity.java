package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.DBManager;
import com.myproject.myapplication.R;
import com.myproject.myapplication.utils.SessionManager;

public class ChangeUsernameActivity extends AppCompatActivity {

    EditText username;

    DBManager db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_username);

        try {
            sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        username = findViewById(R.id.editUsername);
        username.setText(sessionManager.getUsername());
    }

    public void changeUsername(View view) {
        db = new DBManager();
        String currentUsername = username.getText().toString();

        if (!currentUsername.equals(sessionManager.getUsername()) && !currentUsername.isEmpty()) {
            db.changeUsername(sessionManager.getUserId(), currentUsername);
            sessionManager.changeUsername(currentUsername);
        }

        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void returnToSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}