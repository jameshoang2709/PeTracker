package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserLogin extends AppCompatActivity {
    EditText uname;
    EditText pword;
    Button loginButton;
    TextView regText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        uname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);
        regText = (TextView)findViewById(R.id.register);
        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(UserLogin.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }
}