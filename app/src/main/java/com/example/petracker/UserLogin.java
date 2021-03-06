package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
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

        uname = (EditText) findViewById(R.id.username);
        pword = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.login);
        regText = (TextView) findViewById(R.id.register);

        regText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(UserLogin.this, RegisterActivity.class);
                startActivity(registerIntent);
            }
        });
    }

    public void onLoginClick(View view) {
        Log.d("Login", "Login button clicked ----------------------------");
        String user = uname.getText().toString().trim();
        String pswd = pword.getText().toString().trim();
        String checkUser = url + user;
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, checkUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mUser = JsonManager.parseUserData(response).get(0);
                        /*try {
                            JSONObject tempUser = new JSONObject(response);
                            String id = tempUser.getString("id");
                            String usrnm = tempUser.getString("username");
                            String psswrd = tempUser.getString("password");
                            String cust = tempUser.getString("customer");
                            mUser = new User(id, usrnm, psswrd, cust);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }*/
                if (user.contentEquals(mUser.Username) && pswd.contentEquals(mUser.Password)) {
                    Intent loginIntent = new Intent(UserLogin.this, MainActivity.class);
                    loginIntent.putExtra("userId", mUser.id);

                    Toast.makeText(view.getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    // Delay 2s and go back to the parent activity
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                    startActivity(loginIntent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserLogin.this, (CharSequence) error, Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);

    }

    @Override
    public void onBackPressed() {
    }
}