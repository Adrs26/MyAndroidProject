package com.example.guessingnumber;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();

        // Hide Status Bar
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Hide Action Bar
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        // Timer Delay
        new Handler().postDelayed(() -> {
            startActivity(new Intent(MainActivity.this, Start.class));
            finish();
        },3000);
    }
}