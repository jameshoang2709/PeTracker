package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditPersonalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        setTitle(R.string.edit_personal_info_name);
    }
}