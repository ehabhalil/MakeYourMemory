package com.example.mym.model.user;

import android.content.Context;

import androidx.loader.content.AsyncTaskLoader;

import com.example.mym.server.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class UserTaskLoader extends AsyncTaskLoader<List<User>> {
    public UserTaskLoader( Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {
        List<User> users = null;
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
            // TODO: 12/14/2021  get users info from the server
            JSONObject JSONuser = new JSONObject(builder.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return users;
    }

}
