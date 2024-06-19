package com.example.guessingnumber;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.guessingnumber.database.DatabaseGN;
import com.example.guessingnumber.util.AdapterLeaderboard;

public class Leaderboard extends AppCompatActivity {
    private AdapterLeaderboard adapterLeaderboard;
    private Button easyLeaderboard;
    private Button normalLeaderboard;
    private Button hardLeaderboard;
    private ListView listViewLeaderboard;

    private void initComponents() {
        easyLeaderboard = findViewById(R.id.easyLeaderboard);
        normalLeaderboard = findViewById(R.id.normalLeaderboard);
        hardLeaderboard = findViewById(R.id.hardLeaderboard);
        listViewLeaderboard = findViewById(R.id.listViewLeaderboard);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        initComponents();
        DatabaseGN databaseGN = new DatabaseGN(Leaderboard.this);

        adapterLeaderboard = new AdapterLeaderboard(Leaderboard.this, databaseGN.selectDataDifficulty("Easy"));
        listViewLeaderboard.setAdapter(adapterLeaderboard);
        easyLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.green));
        normalLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
        hardLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));

        easyLeaderboard.setOnClickListener(v -> {
            adapterLeaderboard = new AdapterLeaderboard(Leaderboard.this, databaseGN.selectDataDifficulty("Easy"));
            listViewLeaderboard.setAdapter(adapterLeaderboard);
            easyLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.green));
            normalLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
            hardLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
        });
        normalLeaderboard.setOnClickListener(v -> {
            adapterLeaderboard = new AdapterLeaderboard(Leaderboard.this, databaseGN.selectDataDifficulty("Normal"));
            listViewLeaderboard.setAdapter(adapterLeaderboard);
            easyLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
            normalLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.orange));
            hardLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
        });
        hardLeaderboard.setOnClickListener(v -> {
            adapterLeaderboard = new AdapterLeaderboard(Leaderboard.this, databaseGN.selectDataDifficulty("Hard"));
            listViewLeaderboard.setAdapter(adapterLeaderboard);
            easyLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
            normalLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.lightblue));
            hardLeaderboard.setBackgroundColor(ContextCompat.getColor(Leaderboard.this, R.color.red));
        });
        databaseGN.close();
    }
}
