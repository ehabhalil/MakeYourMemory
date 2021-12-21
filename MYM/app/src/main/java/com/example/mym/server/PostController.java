package com.example.mym.server;

import com.example.mym.model.post.Comment;
import com.example.mym.model.post.Like;
import com.example.mym.model.post.Post;
import com.example.mym.model.user.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostController {
    public PostController() {

    }
    public Post getPost(JSONObject post) throws JSONException {
        return handlePost(post.getString("_id"),
                post.getString("text"),
                post.getString("imageURL"),
                new UserController().getUser(post.getJSONObject("user"),true),
                post.getJSONArray("likes"),
                post.getJSONArray("comments"));
    }

    private Post handlePost(String id, String text, String imageURL,User user, JSONArray jLikes, JSONArray jComments) throws JSONException {
        ArrayList<Like> likes = new ArrayList<Like>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (int i = 0; i < jLikes.length(); i++) {
            Like like = new Like(jLikes.getJSONObject(i).getString("user"));
            likes.add(like);
        }
        for (int i = 0; i < jComments.length(); i++) {
            Comment comment = new Comment(jComments.getJSONObject(i).getString("text"),jComments.getJSONObject(i).getJSONObject("user"));
            comments.add(comment);
        }
        return new Post(id,text,imageURL,user,likes,comments);
    }

    public List<Post> getAllPost(JSONArray posts) {
        List<Post> postsList = new ArrayList<Post>();
        try {
            for (int i = 0; i < posts.length(); i++) {
                Post p = getPost(posts.getJSONObject(i));
                postsList.add(p);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postsList;
    }
}
