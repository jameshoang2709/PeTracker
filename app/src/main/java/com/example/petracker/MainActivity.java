package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    static final String USER_ID_STATE_KEY = "userId";
    String userId;
    private String getCustomerUrl;
    TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            userId = savedInstanceState.getString(USER_ID_STATE_KEY);
        }
        else {
            userId = getIntent().getStringExtra("userId");
        }
        getCustomerUrl = "https://rocky-hamlet-24243.herokuapp.com/customerByUserId/" + userId;

        welcome = (TextView) findViewById(R.id.welcome_banner);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest customerRequest = new StringRequest(Request.Method.GET, getCustomerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject customer = new JSONObject(response);
                    welcome.setText("Welcome, " + customer.getString("cust_name") + "!");
                } catch (JSONException e) {e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(customerRequest);

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
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            String custName = data.getStringExtra("name");
            welcome.setText("Welcome, " + custName + "!");
        }
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