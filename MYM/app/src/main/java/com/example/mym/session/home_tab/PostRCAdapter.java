package com.example.mym.session.home_tab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.model.post.Post;
import com.example.mym.model.user.User;
import com.example.mym.server.Constants;
import com.example.mym.server.PostController;
import com.example.mym.server.Server;
import com.example.mym.session.home_tab.post_comments.CommentActivity;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

public class PostRCAdapter extends RecyclerView.Adapter<PostRCAdapter.ViewHolder>{
    private final Fragment fragment;
    Context context;
    List<Post> postsList;
    private User user;
    int iLoader = 1254;
    public PostRCAdapter(Context context, List<Post> postsList, User user, Fragment fragment) {
        this.context = context;
        this.postsList = postsList;
        this.user = user;
        this.fragment = fragment;
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

    class ViewHolder extends RecyclerView.ViewHolder implements LoaderManager.LoaderCallbacks<String> {
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
        int newLikesCount;
        int i ;
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
            this.i = iLoader++;
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    fragment.requireActivity().getSupportLoaderManager().restartLoader(++i,null,ViewHolder.this).forceLoad();
                }
            });
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fragment.requireActivity(), CommentActivity.class);
                    intent.putExtra("post", postsList.get(getAdapterPosition()));
                    fragment.getActivity().startActivity(intent);
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
            likesCount.setText(String.valueOf(postsList.get(getAdapterPosition()).getLikes().size()));
            commentCount.setText(String.valueOf(postsList.get(getAdapterPosition()).getComments().size()));
            description.setText(post.getText());
            userName.setText(post.getText());
            newLikesCount = postsList.get(getAdapterPosition()).getLikes().size();
        }

        @NonNull
        @Override
        public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
            HashMap<String,String> bodyRequest = new HashMap<String, String>();
            bodyRequest.put("userId",user.getUserId());
            bodyRequest.put("postId",postsList.get(this.getAdapterPosition()).getID());
            //System.out.println(bodyRequest.get("userId"));
            server = new Server(context, Constants.VALIDATE_LIKE_WITH_USER,"POST",bodyRequest);
            return server;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<String> loader, String data) {
            if(server.getStatusCode() == 404) {
                newLikesCount += 1;
                likesCount.setText(String.valueOf(newLikesCount));

            }
            else if(server.getStatusCode() == 200){
                newLikesCount -= 1;
                likesCount.setText(String.valueOf(newLikesCount));
            }
            onLoaderReset(loader);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<String> loader) {

        }
    }
}
