package com.example.mym.session.home_tab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.model.post.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostRCAdapter extends RecyclerView.Adapter<PostRCAdapter.ViewHolder> {
    Context context;
    List<Post> postsList;

    public PostRCAdapter(Context context, List<Post> postsList) {
        this.context = context;
        this.postsList = postsList;
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

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        ImageView image;
        TextView description;
        TextView userName;
        Button like;
        Button comment;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            userName = itemView.findViewById(R.id.userName);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
        }

        public void bind(Post post) {
            // TODO: 12/14/2021 set comment class
            Picasso.get().load(post.getImageURL()).into(avatar);
            Picasso.get().load(post.getImageURL()).into(image);
            description.setText(post.getText());
            userName.setText(post.getText());
        }
    }
}
