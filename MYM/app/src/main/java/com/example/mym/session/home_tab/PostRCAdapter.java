package com.example.mym.session.home_tab;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.model.post.Post;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.Server;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class PostRCAdapter extends RecyclerView.Adapter<PostRCAdapter.ViewHolder>{
    Context context;
    List<Post> postsList;
    private User user;
    public PostRCAdapter(Context context, List<Post> postsList,User user) {
        this.context = context;
        this.postsList = postsList;
        this.user = user;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(postsList.get(position));
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements LoaderManager.LoaderCallbacks<String>{
        ImageView avatar;
        ImageView image;
        TextView description;
        TextView userName;
        Button like;
        Button comment;
        TextView likesCount;
        TextView commentCount;
        String postId;
        Server server;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            userName = itemView.findViewById(R.id.userName);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            likesCount = itemView.findViewById(R.id.likeCount);
            commentCount = itemView.findViewById(R.id.commentCount);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Activity activity = (Activity) context;
                    //getSupportLoaderManager().initLoader(i++,null,this).forceLoad();
                }
            });

        }

        public void bind(Post post) {
            // TODO: 12/14/2021 set comment class
            postId = post.getID();
            Picasso.get()
                    .load(post.getImageURL())
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
            //Picasso.get().load(post.getImageURL()).into(avatar);
            //Picasso.get().load(post.getImageURL()).into(image);
            description.setText(post.getText());
            userName.setText(post.getText());
        }

        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            HashMap<String,String> bodyRequest = new HashMap<String, String>();
            bodyRequest.put("userId",user.getUserId());
            bodyRequest.put("postId",user.getUserId());
            server = new Server(context, Constants.VALIDATE_LIKE_WITH_USER,"GET",bodyRequest);
            System.out.println(server.getStatusCode());
            return server;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            int newLikesCount = Integer.parseInt(likesCount.getText().toString())+1;
            if(server.getStatusCode() == 404) {
                likesCount.setText(String.valueOf(newLikesCount));
                like.setBackgroundColor(Color.blue(10));
            }
            else{
                likesCount.setText(String.valueOf(--newLikesCount));
                like.setBackgroundColor(Color.blue(125));
            }
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    }
}
