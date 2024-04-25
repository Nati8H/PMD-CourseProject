package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.DBManager;
import com.myproject.myapplication.R;
import com.myproject.myapplication.utils.SessionManager;

public class ChangeEmailActivity extends AppCompatActivity {

    EditText email;

    DBManager db;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        try {
            sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }

        email = findViewById(R.id.edtUEmail);
        email.setText(sessionManager.getEmail());
    }

    public void changeEmail(View view) {
        db = new DBManager();
        String currentEmail = email.getText().toString();

        if (!currentEmail.equals(sessionManager.getEmail()) && !currentEmail.isEmpty()) {
            db.changeEmail(sessionManager.getUserId(), currentEmail);
            sessionManager.changeEmail(currentEmail);
        }

        startActivity(new Intent(this, SettingsActivity.class));
    }

    public void returnToSettings(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}