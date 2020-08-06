package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class UserLogin extends AppCompatActivity {
    private String url = "https://rocky-hamlet-24243.herokuapp.com/users/";
    private User mUser;
    //private EditAccountInfo.UserRaw mUser;
    EditText uname;
    EditText pword;
    Button loginButton;
    TextView regText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        uname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.login);
        regText = (TextView)findViewById(R.id.register);

        RequestQueue queue = Volley.newRequestQueue(this);

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(UserLogin.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = uname.getText().toString().trim();
                String checkUser = url + user;
                StringRequest stringRequest = new StringRequest(Request.Method.GET, checkUser, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject tempUser = new JSONObject(response);
                            String id = tempUser.getString("_id");
                            String usrnm = tempUser.getString("username");
                            String psswrd = tempUser.getString("password");
                            String cust = tempUser.getString("customer");
                            mUser = new User(id, usrnm, psswrd, cust);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                            mUser = JsonManager.parseUserData(response).get(0);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                //String user = uname.getText().toString().trim();
                Intent loginIntent = new Intent(UserLogin.this, MainActivity.class);
                loginIntent.putExtra("userId", user);
                startActivity(loginIntent);
            }
        });
    }
}