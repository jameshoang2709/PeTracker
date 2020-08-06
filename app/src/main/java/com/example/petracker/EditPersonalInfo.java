package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditPersonalInfo extends AppCompatActivity {

    String addr;
    EditText a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        setTitle(R.string.edit_personal_info_name);
        addr = getIntent().getStringExtra("address");
        a = (EditText) findViewById(R.id.eperi_editText_address);
        a.setText(addr);
    }
}