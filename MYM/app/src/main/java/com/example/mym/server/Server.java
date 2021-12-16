package com.example.mym.server;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;


import com.example.mym.model.user.User;


import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;


public class Server extends AsyncTaskLoader<String> {
    private String urlString;
    private String method;
    private int statusCode;
    private HashMap<String,String> data;
    Context context;

    public Server(Context context,String urlString,String method,HashMap<String,String> data){
        super(context);
        this.context = context;
        this.urlString = urlString;
        this.method = method;
        this.data = data;
        this.statusCode = 0;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        HttpURLConnection urlConnection = null;

        OutputStream out = null;
        InputStream in = null;

        BufferedReader bufferedReader;
        StringBuilder builder;

        byte[] body;
        String line;
        String result = "";

        try {
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            switch (method){
                case "GET":
                    if(urlConnection.getResponseCode() != 200){
                        statusCode = urlConnection.getResponseCode();
                        urlConnection.disconnect();
                        return null;
                    }
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    builder = new StringBuilder();
                    while((line = bufferedReader.readLine()) != null){
                        builder.append(line);
                    }
                    result = builder.toString();
                    statusCode = urlConnection.getResponseCode();
                    break;

                case "POST":
                    urlConnection.setDoOutput(true);
                    urlConnection.setChunkedStreamingMode(0);
                    body = convertToJSON(data);

                    urlConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    out = urlConnection.getOutputStream();
                    out.write(body);
                    System.out.println(urlConnection.getResponseCode());
                    if(urlConnection.getResponseCode() != 200){
                        statusCode = urlConnection.getResponseCode();
                        urlConnection.disconnect();
                        return null;
                    }
                    in = new BufferedInputStream(urlConnection.getInputStream());
                    bufferedReader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    builder = new StringBuilder();
                    while((line = bufferedReader.readLine()) != null){
                        builder.append(line);
                    }
                    result = builder.toString();
                    statusCode = urlConnection.getResponseCode();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            assert urlConnection != null;
            urlConnection.disconnect();
        }
        return result;
    }

    private byte[] convertToJSON(HashMap<String,String> map) {
        return new JSONObject(map).toString().getBytes(StandardCharsets.UTF_8);
    }


    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
