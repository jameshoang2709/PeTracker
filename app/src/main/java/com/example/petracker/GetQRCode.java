package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.net.URL;

public class GetQRCode extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private ImageView qrView;
//    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chl=https%3A%2F%2Frocky-hamlet-24243.herokuapp.com%2FadoptionCenters&chs=512x512";
    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chs=512x512&chl=";
    private String databaseApi = "https%3A%2F%2Frocky-hamlet-24243.herokuapp.com%2Fcustomer%2F";

    String userId = "5f2abeba097fbf4475b98910";
    String userInfoUrl = qrApi + databaseApi + userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_q_r_code);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = GetQRCode.this;

        qrView = (ImageView) findViewById(R.id.qrImageView);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new ImageRequest
        ImageRequest imageRequest = new ImageRequest(
//                qrApi, // Image URL
                userInfoUrl,
                new Response.Listener<Bitmap>() { // Bitmap listener
                    @Override
                    public void onResponse(Bitmap response) {
                        // Do something with response
                        qrView.setImageBitmap(response);

                        // Save this downloaded bitmap to internal storage
                        //Uri uri = saveImageToInternalStorage(response);

                        // Display the internal storage saved image to image view
                        //mImageViewInternal.setImageURI(uri);
                    }
                },
                0, // Image width
                0, // Image height
                ImageView.ScaleType.CENTER_CROP, // Image scale type
                Bitmap.Config.RGB_565, //Image decode configuration
                new Response.ErrorListener() {
                    // Error listener
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something with error response
                        error.printStackTrace();
                        //Snackbar.make(mCLayout,"Error",Snackbar.LENGTH_LONG).show();
                    }
                }
        );

        // Add ImageRequest to the RequestQueue
        requestQueue.add(imageRequest);
    }
}