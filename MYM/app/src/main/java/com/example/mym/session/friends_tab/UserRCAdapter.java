package com.example.mym.session.friends_tab;

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
import com.example.mym.model.user.User;
import com.squareup.picasso.Picasso;

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
        View view = LayoutInflater.from(context).inflate(R.layout.user_card,parent,false);
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
        ImageView userCardAvatar;
        TextView userCardUserName;
        Button relation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userCardAvatar = itemView.findViewById(R.id.userCardAvatar);
            userCardUserName = itemView.findViewById(R.id.userCardUserName);
        }

        public void bind(User user) {
            Picasso.get().load(user.getAvatar()).into(userCardAvatar);
            userCardUserName.setText(user.getFirstName()+" "+user.getLastName());
        }
    }
}
