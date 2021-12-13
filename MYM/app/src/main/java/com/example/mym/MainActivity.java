package com.example.mym;

import android.content.Intent;
import android.os.Bundle;

import com.example.mym.authentication.SignUpActivity;
import com.example.mym.user.DashBoardActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

public class MainActivity extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void authenticateLogin(View view) {
        Intent dashBoardActivity = new Intent(this, DashBoardActivity.class);
        startActivity(dashBoardActivity);
    }

    public void authenticateSignUp(View view) {
        Intent signUpActivity = new Intent(this, SignUpActivity.class);
        startActivity(signUpActivity);
    }
}