package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

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

public class EditAccountInfo extends AppCompatActivity {

    private String userId = "5f2abeba097fbf4475b9890c";
    private String apiURL = "https://rocky-hamlet-24243.herokuapp.com/updateUser/";
    private String updateURL = "https://rocky-hamlet-24243.herokuapp.com/updateUser/" + userId;
    private String getURL = "https://rocky-hamlet-24243.herokuapp.com/user/" + userId;

    private TextView username;
    private EditText curPw;
    private EditText newPw;
    private EditText confirmPw;
    private Button update;

    private UserRaw mUser;

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
                try {
                    JSONObject user = new JSONObject(response);
                    String id = user.getString("_id");
                    String mUsername = user.getString("username");
                    String mPassword = user.getString("password");
                    String mCustomer = user.getString("customer");
                    mUser = new UserRaw(id, mUsername, mPassword, mCustomer);
                    username.setText(mUser.Username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    public void onUpdateClick (View view) {
//        if (curPw.getText().toString().contentEquals(mUser.Password))
    }

    class UserRaw {
        String id;
        String Username;
        String Password;
        String customer;

        public UserRaw(String id, String username, String password, String customer) {
            this.id = id;
            Username = username;
            Password = password;
            this.customer = customer;
        }
    }
}