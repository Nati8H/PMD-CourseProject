package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.DBManager;
import com.myproject.myapplication.R;

public class SignUpActivity extends AppCompatActivity {

    EditText username,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
    }

    public void goToLogin(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void registerUser(View view){
        if(validateData()){
            DBManager db = new DBManager();
            db.addUser(username.getText().toString(),
                       email.getText().toString(),
                       password.getText().toString());
            startActivity(new Intent(this, LoginActivity.class));
        }else{
            Toast t = Toast.makeText(this, "Въведи валидни данни!", Toast.LENGTH_SHORT);
            t.show();

        }

    }

    private boolean validateData(){
        boolean validData = true;

        if (isEmpty(username)) {
            username.setError("Потребителското име е задължително!");
            validData = false;
        }

        if (isEmpty(password)) {
            password.setError("Паролата е задължителна!");
            validData = false;
        }

        if (isEmpty(email)) {
            email.setError("Имейлът е задължителен!");
            validData = false;
        }

        return validData;
    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
}