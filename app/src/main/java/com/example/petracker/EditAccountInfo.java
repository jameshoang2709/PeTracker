package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditAccountInfo extends AppCompatActivity {

    private String userId = "5f2abed1097fbf4475b99735";
    private String apiURL = "https://rocky-hamlet-24243.herokuapp.com/updateUser/";
    private String updateURL = "https://rocky-hamlet-24243.herokuapp.com/updateUser/" + userId;
    private String getURL = "https://rocky-hamlet-24243.herokuapp.com/user/" + userId;

    private TextView username;
    private EditText curPw;
    private EditText newPw;
    private EditText confirmPw;
    private Button update;

    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account_info);
        setTitle(R.string.edit_account_info_name);

        username = (TextView) findViewById(R.id.eai_editText_username);
        curPw = (EditText) findViewById(R.id.eai_editText_curPw);
        newPw = (EditText) findViewById(R.id.eai_editText_newPw);
        confirmPw = (EditText) findViewById(R.id.eai_editText_conPw);
        update = (Button) findViewById(R.id.eai_button_update);

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, getURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                mUser = JsonManager.parseUserData(response).get(0);
                username.setText(mUser.Username);
                //Toast.makeText(getApplicationContext(), "Retrieved user", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
    }

    public void onUpdateClick (View view) {
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        if (curPw.getText().toString().contentEquals(mUser.Password)) {
            if (newPw.getText().toString().contentEquals(confirmPw.getText().toString())) {
                Toast.makeText(view.getContext(), "Saving...", Toast.LENGTH_SHORT).show();

                // Create the JSONObject ready to update
                Map<String, String> params = new HashMap();
                params.put("password", newPw.getText().toString());
                JSONObject parameters = new JSONObject(params);

                JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.PUT, updateURL, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(view.getContext(), "New Password Saved", Toast.LENGTH_SHORT).show();
                        mUser.Password = newPw.getText().toString();
                        curPw.setText("");
                        newPw.setText("");
                        confirmPw.setText("");

                        // Delay 4.5s and go back to the parent activity
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 4500);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                queue.add(stringRequest);
                curPw.setText("");
                newPw.setText("");
                confirmPw.setText("");
            }
            else { // newPw != confirmPw
                Toast.makeText(this, "Confirm Password doesn't match New Password!", Toast.LENGTH_SHORT).show();
                curPw.setText("");
                newPw.setText("");
                confirmPw.setText("");
            }
        }
        else { // curPw doesn't match
            Toast.makeText(this, "Incorrect Current Password!", Toast.LENGTH_SHORT).show();
            curPw.setText("");
            newPw.setText("");
            confirmPw.setText("");
        }
    }
}