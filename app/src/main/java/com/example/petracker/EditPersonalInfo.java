package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditPersonalInfo extends AppCompatActivity {

    private final String apiUrl = "https://rocky-hamlet-24243.herokuapp.com/";
    private String userId;
    private String getCustomerUrl;

    private String updateUrl = "https://rocky-hamlet-24243.herokuapp.com/updateCustomer/";
//    private String getCustomerUrl;

    private String customerId;

    EditText name;
    EditText email;
    EditText phone;
    Button updateButton;

    String currentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        setTitle(R.string.edit_personal_info_name);

        userId = getIntent().getStringExtra("userId");
        getCustomerUrl = "https://rocky-hamlet-24243.herokuapp.com/customerByUserId/" + userId;

        name = (EditText) findViewById(R.id.eperi_editText_name);
        email = (EditText) findViewById(R.id.eperi_editText_email);
        phone = (EditText) findViewById(R.id.eperi_editTextPhone_phone);
        updateButton = (Button) findViewById(R.id.eperi_button_update);

//        final String[] currentName = new String[1];

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest customerRequest = new StringRequest(Request.Method.GET, getCustomerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject mCustomer = new JSONObject(response);
                    customerId = mCustomer.getString("_id");
                    name.setText(mCustomer.getString("cust_name"));
                    currentName = mCustomer.getString("cust_name");
                    email.setText(mCustomer.getString("cust_email"));
                    phone.setText(mCustomer.getString("cust_phone"));
                } catch (JSONException e) {e.printStackTrace();}
//                customerId = response;
//                getCustomerUrl = apiUrl + "customerRaw/" + response;
                //Toast.makeText(getApplicationContext(), "Retrieved user", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(customerRequest);

        // Update button click listener -------------------------------------------------------
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mName = name.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                String mPhone = phone.getText().toString().trim();

                Map<String, String> params = new HashMap<String, String>();
                params.put("cust_name", mName);
                params.put("cust_email", mEmail);
                params.put("cust_phone", mPhone);

                JSONObject updatedCust = new JSONObject(params);

                RequestQueue updateQueue = Volley.newRequestQueue(view.getContext());

                Toast.makeText(view.getContext(), "Updating...", Toast.LENGTH_SHORT).show();
                JsonObjectRequest updateRequest = new JsonObjectRequest(Request.Method.PUT, updateUrl.concat(customerId), updatedCust, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(view.getContext(), "Information updated", Toast.LENGTH_SHORT).show();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Intent intent = new Intent();
                                intent.putExtra("name", mName);
                                setResult(2, intent);
                                finish();
                            }
                        }, 4500);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(), "Couldn't update", Toast.LENGTH_SHORT).show();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2500);
                    }
                });

                updateQueue.add(updateRequest);
            }
        });
    }

    public void onPersonalBackClick (View view) {
        Intent intent = new Intent();
        intent.putExtra("name", currentName);
        setResult(2, intent);
        finish();
    }
    // Override the back button on the Action bar
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                Toast.makeText(this, "Back button clicked", Toast.LENGTH_SHORT).show();
//                finish();
//                break;
//        }
//        return true;
//    }

}