package com.example.mym.session.home_tab.post_comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;
import com.example.mym.server.model.post.Comment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RCCommentAdapter extends RecyclerView.Adapter<RCCommentAdapter.ViewHolder>{
    private Context commentActivity;
    private ArrayList<Comment> comments;

    public RCCommentAdapter(CommentActivity commentActivity, ArrayList<Comment> comments) {
        this.commentActivity = commentActivity;
        this.comments = comments;
    }

    @NonNull
    @Override
    public RCCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(commentActivity).inflate(R.layout.comment_card,parent,false);
        return new RCCommentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RCCommentAdapter.ViewHolder holder, int position) {
        holder.bind(comments.get(position));
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView userName;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatarC);
            userName = itemView.findViewById(R.id.userNameC);
            textView = itemView.findViewById(R.id.textViewC);
        }

        public void bind(Comment comment) {
            Picasso.get()
                    .load(comment.getImageUrl())
                    .placeholder(R.mipmap.ic_launcher)
                    .fit()
                    .centerCrop()
                    .into(avatar);
            userName.setText(comment.getUserName());
            textView.setText(comment.getText());
        }
    }
}
