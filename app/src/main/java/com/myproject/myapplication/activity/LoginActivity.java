package com.myproject.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.myproject.myapplication.DBManager;
import com.myproject.myapplication.R;
import com.myproject.myapplication.model.User;
import com.myproject.myapplication.utils.AESCrypt;
import com.myproject.myapplication.utils.SessionManager;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    private DBManager db;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        try {
             sessionManager = new SessionManager(getApplicationContext());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
    }

    public void loginUser(View view) {
        String currentEmail = email.getText().toString();
        String currentPassword = password.getText().toString();

        User currentUser = getCurrentUser(currentEmail,currentPassword);

        if(currentUser==null){
            Toast.makeText(this, "Потребителското име или паролата са грешни!", Toast.LENGTH_SHORT).show();
        }else {
            sessionManager.loginUser(currentUser.getId(), currentUser.getUsername(), currentUser.getEmail(), currentUser.getPassword());
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    public void goToRegister(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private User getCurrentUser(String email,String password){
        db = new DBManager();
        List<User> users = db.getAllUsers();

        try {
            for (User user:users){
                String decryptedPassword = AESCrypt.decrypt(user.getPassword());
                if (user.getEmail().equals(email)&& decryptedPassword.equals(password)){
                    return user;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }


        return null;
    }
}