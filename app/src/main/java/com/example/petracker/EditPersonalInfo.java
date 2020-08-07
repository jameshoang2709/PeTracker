package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditPersonalInfo extends AppCompatActivity {

    private String userId = "5f2abed1097fbf4475b99735";
    private String getURL = "https://rocky-hamlet-24243.herokuapp.com/customerRaw/" + userId;
//    private String updateURL =

    EditText name;
    EditText email;
    EditText phone;
    Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        setTitle(R.string.edit_personal_info_name);

        name = (EditText) findViewById(R.id.eperi_editText_name);
        email = (EditText) findViewById(R.id.eperi_editText_email);
        phone = (EditText) findViewById(R.id.eperi_editTextPhone_phone);
        updateButton = (Button) findViewById(R.id.eperi_button_update);
    }
}