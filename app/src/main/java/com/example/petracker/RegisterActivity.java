package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private String url = "https://rocky-hamlet-24243.herokuapp.com/users/";
    private User mUser;
    private Customer mCust;
    EditText uname;
    EditText pword;
    EditText cnfPword;
    EditText cName;
    EditText emailAddr;
    EditText phoneNum;
    Button regButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        uname = (EditText)findViewById(R.id.username);
        pword = (EditText)findViewById(R.id.password);
        cnfPword = (EditText)findViewById(R.id.cnf_password);
        cName = (EditText)findViewById(R.id.customerName);
        emailAddr = (EditText)findViewById(R.id.email);
        phoneNum = (EditText)findViewById(R.id.phoneNumber);
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
        RequestQueue queue = Volley.newRequestQueue(view.getContext());
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

        if (!user.equals(mUser.Username)) {
            if (pswd.equals(cnfPswd)) {
                /*Customer cust = new Customer();
                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, updateURL, parameters, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(this, "You have been Registered!", Toast.LENGTH_SHORT).show();
                        mUser.Password = newPw.getText().toString();
                        curPw.setText("");
                        newPw.setText("");
                        confirmPw.setText("");
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });*/
                Intent registerIntent = new Intent(RegisterActivity.this, MainActivity.class);
                registerIntent.putExtra("userId", user);
                startActivity(registerIntent);
            } else {
                Toast.makeText(this, "The two passwords don't match. Please try again!", Toast.LENGTH_SHORT).show();
                uname.setText("");
                pword.setText("");
                cnfPword.setText("");
            }
        } else {
            Toast.makeText(this, "This user already exists!", Toast.LENGTH_SHORT).show();
            uname.setText("");
            pword.setText("");
            cnfPword.setText("");
        }
    }
}