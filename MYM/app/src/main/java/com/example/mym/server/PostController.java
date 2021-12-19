package com.example.mym.server;

import com.example.mym.model.post.Comment;
import com.example.mym.model.post.Like;
import com.example.mym.model.post.Post;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostController {
    // TODO: 12/14/2021 get all posts for dashboard
    // TODO: 12/14/2021 add new post
    // TODO: 12/14/2021 update post
    // TODO: 12/14/2021 delete post
    // TODO: 12/14/2021 get all posts for profile with user id
    private Post post;

    public PostController() {

    }
    public Post getPost(JSONObject post) throws JSONException {
        return handlePost(post.getString("_id"),post.getString("text"),post.getString("imageURL"),post.getJSONArray("likes"),post.getJSONArray("comments"));
    }

    private Post handlePost(String id, String text, String imageURL, JSONArray jLikes, JSONArray jComments) throws JSONException {
        ArrayList<Like> likes = new ArrayList<Like>();
        ArrayList<Comment> comments = new ArrayList<Comment>();
        for (int i = 0; i < jLikes.length(); i++) {
            Like like = new Like(jLikes.getJSONObject(i).getString("user"));
            likes.add(like);
        }
        for (int i = 0; i < jComments.length(); i++) {
            Comment comment = new Comment(jComments.getJSONObject(i).getString("text"),jComments.getJSONObject(i).getString("user"));
            comments.add(comment);
        }
        return new Post(id,text,imageURL,likes,comments);
    }

    public void setPost(Post post) {
        this.post = post;
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
