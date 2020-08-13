package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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

public class EditPersonalInfo extends AppCompatActivity {

    private final String apiUrl = "https://rocky-hamlet-24243.herokuapp.com/";
    private String userId;
    private String getCustomerUrl;
    private String customerId;
    private String updateUrl = "https://rocky-hamlet-24243.herokuapp.com/updateCustomer/";
//    private String getCustomerUrl;

    EditText name;
    EditText email;
    EditText phone;
    Button updateButton;

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

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest customerRequest = new StringRequest(Request.Method.GET, getCustomerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject mCustomer = new JSONObject(response);
                    name.setText(mCustomer.getString("cust_name"));
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
    }
}