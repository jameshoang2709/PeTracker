package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {
    private String url = "https://rocky-hamlet-24243.herokuapp.com/users/";
    private User mUser;
    EditText uname;
    EditText pword;
    EditText cnfPword;
    Button regButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        cnfPword = (EditText)findViewById(R.id.cnf_password);
        regButton = (Button)findViewById(R.id.register);
        loginText = (TextView)findViewById(R.id.already_reg);
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, UserLogin.class);
                startActivity(loginIntent);
            }
        });
    }

    public void onRegisterClick (View view) {
        String user = uname.getText().toString().trim();
        String pswd = pword.getText().toString().trim();
        String cnfPswd = cnfPword.getText().toString().trim();
        String checkUser = url + user;
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mUser = JsonManager.parseUserData(response).get(0);
                        /*try {
                            JSONObject tempUser = new JSONObject(response);
                            String id = tempUser.getString("_id");
                            String usrnm = tempUser.getString("username");
                            String psswrd = tempUser.getString("password");
                            String cust = tempUser.getString("customer");
                            mUser = new User(id, usrnm, psswrd, cust);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

        if (pswd.equals(cnfPswd)) {
            Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
            registerIntent.putExtra("userId", user);
            startActivity(registerIntent);
        }
    }
}