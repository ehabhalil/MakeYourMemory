package com.example.mym;

import android.content.Context;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.ViewHolder> {
    Context context;
    List<Post> postsList;

    public RCAdapter(Context context, List<Post> postsList) {
        this.context = context;
        this.postsList = postsList;
    }

    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
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
            ImageView avatar = itemView.findViewById(R.id.avatar);
            ImageView image = itemView.findViewById(R.id.image);
            TextView description = itemView.findViewById(R.id.description);
            TextView userName = itemView.findViewById(R.id.userName);
            Button like = itemView.findViewById(R.id.like);
            Button comment = itemView.findViewById(R.id.comment);
        }

        public void bind(Post post) {
            //description.setText(post.getDescription());
            //Picasso.with(context).load().into;
        }
    }
}
