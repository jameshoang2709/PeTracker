package com.example.petracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    private Button saveQRButton;
    private ImageView qrView;
//    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chl=https%3A%2F%2Frocky-hamlet-24243.herokuapp.com%2FadoptionCenters&chs=512x512";
    private String qrApi = "https://chart.googleapis.com/chart?cht=qr&chs=512x512&chl=";
    private String databaseApi = "https://rocky-hamlet-24243.herokuapp.com/customer/";

    private String userId;
    private String userInfoUrl;
    private String getCustomerUrl;

    public static final ExecutorService apiExecutor = Executors.newFixedThreadPool(4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_q_r_code);

        userId = getIntent().getStringExtra("userId");
//        userInfoUrl = databaseApi + userId;
        getCustomerUrl = "https://rocky-hamlet-24243.herokuapp.com/customerByUserId/" + userId;

        // Get the application context
        mContext = getApplicationContext();
        mActivity = GetQRCode.this;

        saveQRButton = (Button) findViewById(R.id.saveQR_button);
        qrView = (ImageView) findViewById(R.id.qrImageView);

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        StringRequest customerRequest = new StringRequest(Request.Method.GET, getCustomerUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject customer = new JSONObject(response);
                    userInfoUrl = databaseApi + customer.getString("_id");

                    RequestQueue imageQueue = Volley.newRequestQueue(mContext);
                    // Initialize a new ImageRequest
                    ImageRequest imageRequest = new ImageRequest(
//                qrApi, // Image URL
                            qrApi + encodeValue(userInfoUrl),
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

                } catch (JSONException e) {e.printStackTrace();}

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(customerRequest);
    }

    // Method to encode a string value using `UTF-8` encoding scheme
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }

    public void onSaveClick (View view) {
        BitmapDrawable drawable = (BitmapDrawable) qrView.getDrawable();
        Bitmap qr = drawable.getBitmap();

        try {
            FileOutputStream outStream = null;
            File sdCard = Environment.getExternalStorageDirectory();
//            File dir = new File(sdCard.getAbsolutePath() + File.separator + "PeTracker");
            File dir = new File(sdCard.getAbsolutePath() + "/Pictures/PeTracker");
            Log.d("FilePath", dir.getAbsolutePath());
            dir.mkdirs();

            String fileName = String.format("%d.jpg", System.currentTimeMillis());
            File outFile = new File(dir, fileName);
            outStream = new FileOutputStream(outFile);
            qr.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();
            Toast.makeText(this, "QR code saved to Gallery", Toast.LENGTH_SHORT).show();

            //Delay
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);

        } catch (FileNotFoundException e) {
            Log.e("SaveQR", e.getLocalizedMessage());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("SaveQR", e.getLocalizedMessage());
            e.printStackTrace();
        }

    }

    public void onQRBackClick (View view) {
        finish();
    }
}