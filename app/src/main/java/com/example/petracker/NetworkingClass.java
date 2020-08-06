package com.example.petracker;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkingClass {

    APIDataListener activity;
    Context mainActivityContext;

    public interface APIDataListener{
        public void returnAPIData(String data);
    }

    public NetworkingClass(APIDataListener listener, Context context) {
        this.activity = listener;
        this.mainActivityContext = context;
    }

    void getDataFromApi(String URL){
        final String url = "https://rocky-hamlet-24243.herokuapp.com/" + URL;
        connectApi(url);
    }

    void connectApi(final String url){
        try {

            Thread thread = new Thread(){
                public void run(){
                    Looper.prepare();

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            String data = "";
                            HttpURLConnection httpURLConnection = null;
                            try{
                                httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setRequestProperty("Content-Type", "application/json");

                                int status = httpURLConnection.getResponseCode();
                                Log.d("GET RX", "Status=> " + status);

                                try{
                                    InputStream in = httpURLConnection.getInputStream();
                                    InputStreamReader inputStreamReader = new InputStreamReader(in);
                                    int inputStreamData = inputStreamReader.read();
                                    while (inputStreamData != -1){
                                        char current = (char) inputStreamData;
                                        inputStreamData = inputStreamReader.read();
                                        data += current;
                                    }

                                    final String finalData = data;
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        @Override
                                        public void run() {
                                            activity.returnAPIData(finalData);
                                        }
                                    });
                                }catch (Exception ex){
                                    Log.d("error", ex.toString());
                                }
                            }catch (Exception ex){
                                Log.d("TX", "Error=> " + ex.getMessage());
                                ex.printStackTrace();
                            }finally {
                                if(httpURLConnection != null){
                                    httpURLConnection.disconnect();
                                }
                            }

                            handler.removeCallbacks(this);
                            Looper.myLooper().quit();
                        }
                    },1000);

                    Looper.loop();
                }
            };

            thread.start();

        }catch (Exception ex){
            Log.e("ERROR => ","" + ex.getMessage() );
            ex.printStackTrace();
        }
    }
}

