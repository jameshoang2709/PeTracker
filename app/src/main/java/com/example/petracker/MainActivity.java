package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    static final String USER_ID_STATE_KEY = "userId";
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = getIntent().getStringExtra("userId");

//        if (savedInstanceState != null) {
//            userId = savedInstanceState.getString(USER_ID_STATE_KEY);
//        }
//        else {
//            userId = getIntent().getStringExtra("userId");
//        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(USER_ID_STATE_KEY, userId);
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        userId = savedInstanceState.getString(USER_ID_STATE_KEY);
    }

    public void toEditPetInfo (View view) {
        Intent intent = new Intent(this, EditPetInfo.class);
        startActivity(intent);
    }

    public void toEditAccountInfo (View view) {
        Intent intent = new Intent(this, EditAccountInfo.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void toEditPersonalInfo (View view) {
        Intent intent = new Intent(this, EditPersonalInfo.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void toTrackGPSActivity (View view){
        Intent intent = new Intent(this,GPS_track_activity.class);
        startActivity(intent);
    }

    public void toGetQRCode (View view) {
        Intent intent = new Intent(this, GetQRCode.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
    }

    public void toLogin (View view) {
        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }

}