package com.example.mym;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class UserTaskLoader extends AsyncTaskLoader<User> {
    public UserTaskLoader( Context context) {
        super(context);
    }

    @Override
    public User loadInBackground() {
        User user = null;
        try {
            URL url = new URL(Constants.SERVER_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line;

            while((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            JSONObject JSONuser = new JSONObject(builder.toString());
            user = new User(JSONuser.getString("userName"),null,null,null,null,null,null);
            //Log.d("her",user.getUserName());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return user;
    }

}
