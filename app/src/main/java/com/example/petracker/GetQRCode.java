package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GetQRCode extends AppCompatActivity {
    private Context mContext;
    private Activity mActivity;

    private ImageView qrView;
//    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chl=https%3A%2F%2Frocky-hamlet-24243.herokuapp.com%2FadoptionCenters&chs=512x512";
    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chs=512x512&chl=";
    private String databaseApi = "https://rocky-hamlet-24243.herokuapp.com/customer/";

    String userId = "5f2abeba097fbf4475b9890c";
    String userInfoUrl = databaseApi + userId;

    String qrURL;
    public static final ExecutorService apiExecutor = Executors.newFixedThreadPool(4);

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

        RequestFuture<String> future = RequestFuture.newFuture();
        // Get customer's info - Volley WILL run stringRequest FIRST then imageRequest
        StringRequest stringRequest = new StringRequest(Request.Method.GET, userInfoUrl, future, future);

        requestQueue.add(stringRequest);


        apiExecutor.execute(()-> {
            try {
                String response = future.get(30, TimeUnit.SECONDS); // This will block the requestQueue execution
                String encodedInfo = encodeValue(response);
                qrURL = qrApi + encodedInfo;
                Log.d("GetInfoThread", qrURL);



                // Initialize a new ImageRequest
                ImageRequest imageRequest = new ImageRequest(
//                qrApi, // Image URL
                        qrURL,
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
                requestQueue.add(imageRequest);


            } catch (InterruptedException e) { }
            catch (ExecutionException e) {}
            catch (TimeoutException e) {
                Log.d("GetCustomerInfo", e.toString());
            }
        });
        // Add ImageRequest to the RequestQueue
//        requestQueue.add(imageRequest);
    }

    // Method to encode a string value using `UTF-8` encoding scheme
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}