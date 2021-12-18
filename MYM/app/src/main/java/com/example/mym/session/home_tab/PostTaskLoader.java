package com.example.mym.session.home_tab;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.example.mym.model.post.Post;
import com.example.mym.server.Constants;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PostTaskLoader extends AsyncTaskLoader<List<Post>> {
    public PostTaskLoader( Context context) {
        super(context);
    }
    HttpURLConnection connection;
    @Override
    public List<Post> loadInBackground() {
        List<Post> postsList = new ArrayList<Post>();
        try {
            URL url = new URL(Constants.CREATE_NEW_POST);
            connection = (HttpURLConnection) url.openConnection();
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
            JSONArray posts = new JSONArray(builder.toString());
            System.out.println(posts);
            for (int i = 0; i < posts.length(); i++) {
                Post p = new Post(posts.getJSONObject(i).getString("text"),posts.getJSONObject(i).getString("imageURL"));
                postsList.add(p);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

        return postsList;
    }

}
