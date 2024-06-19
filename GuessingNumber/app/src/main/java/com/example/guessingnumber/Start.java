package com.example.guessingnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guessingnumber.database.entity.UserModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Start extends AppCompatActivity {
    private TextInputEditText username;
    private Button easyButton;
    private Button normalButton;
    private Button hardButton;
    private Button leaderboardButton;

    private void initComponents() {
        username = findViewById(R.id.usernameInputLayout).findViewById(R.id.usernameInputEditText);
        easyButton = findViewById(R.id.easyModeButton);
        normalButton = findViewById(R.id.normalModeButton);
        hardButton = findViewById(R.id.hardModeButton);
        leaderboardButton = findViewById(R.id.leaderboardButton);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        initComponents();

        easyButton.setOnClickListener(v -> {
            String stringUsername = Objects.requireNonNull(username.getText()).toString();
            if (stringUsername.isEmpty()) {
                Toast.makeText(Start.this, "Harap masukan username", Toast.LENGTH_LONG).show();
            } else {
                UserModel user = new UserModel(stringUsername, "Easy", 0);
                UserModel.setStaticUsername(user.getUsername());
                UserModel.setStaticDifficulty(user.getDifficulty());
                UserModel.setStaticScore(user.getScore());

                Intent intent = new Intent (Start.this, EasyRules.class);
                startActivity(intent);
            }
        });
        normalButton.setOnClickListener(v -> {
            String stringUsername = Objects.requireNonNull(username.getText()).toString();
            if (stringUsername.isEmpty()) {
                Toast.makeText(Start.this, "Harap masukan username", Toast.LENGTH_LONG).show();
            } else {
                UserModel user = new UserModel(stringUsername, "Normal", 0);
                UserModel.setStaticUsername(user.getUsername());
                UserModel.setStaticDifficulty(user.getDifficulty());
                UserModel.setStaticScore(user.getScore());

                Intent intent = new Intent (Start.this, NormalRules.class);
                startActivity(intent);
            }
        });
        hardButton.setOnClickListener(v -> {
            String stringUsername = Objects.requireNonNull(username.getText()).toString();
            if (stringUsername.isEmpty()) {
                Toast.makeText(Start.this, "Harap masukan username", Toast.LENGTH_LONG).show();
            } else {
                UserModel user = new UserModel(stringUsername, "Hard", 0);
                UserModel.setStaticUsername(user.getUsername());
                UserModel.setStaticDifficulty(user.getDifficulty());
                UserModel.setStaticScore(user.getScore());

                Intent intent = new Intent (Start.this, HardRules.class);
                startActivity(intent);
            }
        });
        leaderboardButton.setOnClickListener(v -> {
            Intent intent = new Intent (Start.this, Leaderboard.class);
            startActivity(intent);
        });
    }
}
