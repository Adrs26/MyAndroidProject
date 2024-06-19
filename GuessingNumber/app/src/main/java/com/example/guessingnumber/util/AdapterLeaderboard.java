package com.example.guessingnumber.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.guessingnumber.R;
import com.example.guessingnumber.database.entity.UserModel;

import java.util.List;

public class AdapterLeaderboard extends ArrayAdapter<UserModel> {
    private final Context context;
    private final List<UserModel> listUser;

    public AdapterLeaderboard(Context context, List<UserModel> listUser) {
        super(context, 0, listUser);
        this.context = context;
        this.listUser = listUser;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listUser = convertView;
        if (listUser == null) {
            listUser = LayoutInflater.from(context).inflate(R.layout.listview_leaderboard_template, parent, false);
        }

        UserModel currentUser = this.listUser.get(position);

        TextView ranking = listUser.findViewById(R.id.leaderboardRanking);
        TextView username = listUser.findViewById(R.id.leaderboardUsername);
        TextView score = listUser.findViewById(R.id.leaderboardScore);

        ranking.setText(String.valueOf(currentUser.getRanking()));
        username.setText(String.valueOf(currentUser.getUsername()));
        score.setText(String.valueOf(currentUser.getScore()));

        return listUser;
    }
}
