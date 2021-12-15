package com.example.mym.session.friends_tab;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.example.mym.model.post.Post;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class UserTaskLoader extends AsyncTaskLoader<List<User>> {
    public UserTaskLoader( Context context) {
        super(context);
    }

    @Override
    public List<User> loadInBackground() {
        List<User> usersList = new ArrayList<User>();
        try {
            URL url = new URL(Constants.GET_ALL_USERS);
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
            JSONArray users = new JSONArray(builder.toString());
            System.out.println(users);

            for (int i = 0; i < users.length(); i++) {


                User p = new User(users.getJSONObject(i).getString("userName"),
                        users.getJSONObject(i).getString("firstName"),
                        users.getJSONObject(i).getString("lastName"),
                        users.getJSONObject(i).getString("password_hash"),
                        users.getJSONObject(i).getString("userName"),
                        users.getJSONObject(i).getString("avatar"),null
                        //users.getJSONObject(i).getJSONArray("friends")
                        );
                usersList.add(p);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return usersList;
    }

}
