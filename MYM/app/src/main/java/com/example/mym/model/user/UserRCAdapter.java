package com.example.mym.model.user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mym.R;

import java.util.List;

public class UserRCAdapter extends RecyclerView.Adapter<UserRCAdapter.ViewHolder> {
    Context context;
    List<User> usersList;

    public UserRCAdapter(Context context, List<User> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(usersList.get(position));
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        /*
        ImageView avatar;
        ImageView image;
        TextView description;
        TextView userName;
        Button like;
        Button comment;
        */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            /*
            avatar = itemView.findViewById(R.id.avatar);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);
            userName = itemView.findViewById(R.id.userName);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            */
        }

        public void bind(User user) {
            /*
            Picasso.get().load(post.getImageURL()).into(avatar);
            Picasso.get().load(post.getImageURL()).into(image);
            description.setText(post.getText());
            userName.setText(post.getText());
            */
        }
    }
}
