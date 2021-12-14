package com.example.mym.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mym.session.MainActivity;
import com.example.mym.R;

public class LoginActivity extends AppCompatActivity {
    // TODO: 12/14/2021 implement taskLoader to make authentication and  initialize user
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
    }
    public void authenticateLogin(View view) {
        // TODO: 12/14/2021 implement sign in and set bundle for user
        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }
    public void authenticateSignUp(View view) {
        // TODO: 12/14/2021 implement signup
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }
}