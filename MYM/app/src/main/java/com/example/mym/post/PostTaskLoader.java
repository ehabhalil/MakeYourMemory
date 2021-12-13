package com.example.mym.post;

import android.content.Context;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import com.example.mym.Constants;

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

    @Override
    public List<Post> loadInBackground() {
        List<Post> postList = new ArrayList<Post>();
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
            JSONArray posts = new JSONArray(builder.toString());
            System.out.println(posts);
            for (int i = 0; i < posts.length(); i++) {


                Post p = new Post(posts.getJSONObject(i).getString("text"),posts.getJSONObject(i).getString("imageURL"));
                Log.d("TAG", p.toString());
                postList.add(p);
            }
            Log.d("TAG", postList.toString());
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return postList;
    }

}
