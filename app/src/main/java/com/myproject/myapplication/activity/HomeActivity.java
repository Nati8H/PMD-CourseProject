package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myproject.myapplication.R;
import com.myproject.myapplication.utils.SessionManager;

public class HomeActivity extends AppCompatActivity {

    TextView userName;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        userName = findViewById(R.id.userName);
        userName.setText(String.format("Добре дошъл/ла, %s!", sessionManager.getUsername()));
    }

    public void countStepsButton(View view) {
        startActivity(new Intent(this, StepsActivity.class));
    }

    public void accountSettingsButton(View view) {
        startActivity(new Intent(this, SettingsActivity.class));
    }
}