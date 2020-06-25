package com.example.petrackerviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class EditPetInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.edit_pet_info_name);
        setContentView(R.layout.activity_edit_pet_info);

    }
}