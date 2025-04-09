package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, emailText;
        ImageView thumbnail;

        public UserViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.name);
            emailText = itemView.findViewById(R.id.email);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.nameText.setText(user.name.first + " " + user.name.last);
        holder.emailText.setText(user.email);
        Glide.with(holder.itemView.getContext())
                .load(user.picture.thumbnail)
                .into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
