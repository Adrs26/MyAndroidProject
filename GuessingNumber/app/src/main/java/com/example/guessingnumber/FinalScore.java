package com.example.guessingnumber;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessingnumber.database.DatabaseGN;
import com.example.guessingnumber.database.entity.UserModel;

public class FinalScore extends AppCompatActivity {
    private TextView username;
    private TextView difficulty;
    private TextView finalScore;
    private TextView numberAnswered;
    private Button saveButton;
    private Button backButton;

    private void initComponents() {
        username = findViewById(R.id.username);
        difficulty = findViewById(R.id.difficulty);
        finalScore = findViewById(R.id.finalScore);
        numberAnswered = findViewById(R.id.numberAnswered);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_score);

        initComponents();

        username.setText(getResources().getString(R.string.finalScoreTemplate, UserModel.getStaticUsername()));
        difficulty.setText(getResources().getString(R.string.finalScoreTemplate, UserModel.getStaticDifficulty()));
        finalScore.setText(getResources().getString(R.string.finalScoreTemplate, String.valueOf(UserModel.getStaticScore())));
        numberAnswered.setText(getResources().getString(R.string.finalScoreTemplate, String.valueOf(UserModel.getNumberAnswered())));

        saveButton.setOnClickListener(v -> {
            DatabaseGN databaseGN = new DatabaseGN(FinalScore.this);
            databaseGN.insertData(UserModel.getStaticUsername(), UserModel.getStaticDifficulty(), UserModel.getStaticScore());
            databaseGN.close();

            Intent intent = new Intent (FinalScore.this, Start.class);
            startActivity(intent);
        });
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent (FinalScore.this, Start.class);
            startActivity(intent);
        });
    }
}
