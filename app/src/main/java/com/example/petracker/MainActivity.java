package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toEditPetInfo (View view) {
        Intent intent = new Intent(this, EditPetInfo.class);
        startActivity(intent);
    }

    public void toEditAccountInfo (View view) {
        Intent intent = new Intent(this, EditAccountInfo.class);
        startActivity(intent);
    }

    public void toEditPersonalInfo (View view) {
        Intent intent = new Intent(this, EditPersonalInfo.class);
        startActivity(intent);
    }

    public void toTrackGPSActivity (View view){
        Intent intent = new Intent(this,GPS_track_activity.class);
        startActivity(intent);
    }
}