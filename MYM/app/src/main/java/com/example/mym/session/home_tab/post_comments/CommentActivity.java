package com.example.mym.session.home_tab.post_comments;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.model.post.Comment;
import com.example.mym.model.post.Post;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.PostController;
import com.example.mym.server.Server;
import com.example.mym.session.home_tab.PostRCAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CommentActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    int i = 2840;
    Server server;
    Post post;
    User user;
    EditText commentDiscrepitonCS;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        post = (Post) getIntent().getSerializableExtra("post");
        user = post.getUser();
        setContentView(R.layout.activity_comment);
        ImageView avatar = findViewById(R.id.avatarCS);
        ImageView image = findViewById(R.id.imageCS);
        TextView userName = findViewById(R.id.userNameCS);
        TextView description = findViewById(R.id.descriptionCS);
        Button CommentButtonCS = findViewById(R.id.CommentButtonCS);
        commentDiscrepitonCS = findViewById(R.id.commentDiscrepitonCS);
        CommentButtonCS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(commentDiscrepitonCS.getText().toString().equals("")){
                    Toast.makeText(CommentActivity.this, "can not post empty comment",Toast.LENGTH_SHORT).show();
                }
                else {
                    CommentActivity.this.getSupportLoaderManager().initLoader(i++,null,CommentActivity.this).forceLoad();
                }
            }
        });
        Picasso.get()
                .load(user.getAvatar())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(avatar);
        Picasso.get()
                .load(post.getImageURL())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(image);
        description.setText(post.getText());
        userName.setText(user.getUserName());

        RecyclerView commentList = findViewById(R.id.commentList);
        commentList.setLayoutManager(new LinearLayoutManager(this));
        RCCommentAdapter adapter = new RCCommentAdapter(this,post.getComments());
        commentList.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        HashMap<String,String> bodyRequest = new HashMap<String, String>();
        bodyRequest.put("userId",user.getUserId());
        bodyRequest.put("postId",post.getID());
        bodyRequest.put("imageURL", user.getAvatar());
        bodyRequest.put("text",commentDiscrepitonCS.getText().toString());
        bodyRequest.put("userName",user.getUserName());
        server = new Server(this, Constants.ADD_NEW_COMMENT,"POST",bodyRequest);
        return server;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Toast.makeText(this, "commented", Toast.LENGTH_SHORT).show();
        this.finish();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
