package com.example.petrackerviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditAccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);
        setTitle(R.string.edit_account_info_name);
    }
}