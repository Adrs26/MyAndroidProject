package com.example.guessingnumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.guessingnumber.database.entity.UserModel;
import com.example.guessingnumber.util.NumberGenerator;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class EasyMode extends AppCompatActivity {
    private TextView score;
    private TextView stage;
    private TextInputEditText guess;
    private TextView hint;
    private Button guessButton;

    private void initComponents() {
        score = findViewById(R.id.easyScore);
        stage = findViewById(R.id.easyStage);
        guess = findViewById(R.id.easyInputLayout).findViewById(R.id.easyInputEditText);
        hint = findViewById(R.id.easyHint);
        guessButton = findViewById(R.id.easyGuessButton);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);

        initComponents();

        AtomicInteger currentScore = new AtomicInteger(0);
        AtomicInteger currentStage = new AtomicInteger(1);
        AtomicInteger currentAttempt = new AtomicInteger(0);
        AtomicInteger numberAnswered = new AtomicInteger(0);
        AtomicInteger currentNumber = new AtomicInteger(NumberGenerator.easyNumberGenerator());

        score.setText(String.valueOf(currentScore.get()));
        stage.setText(getResources().getString(R.string.stage, currentStage.get()));
        hint.setVisibility(View.INVISIBLE);

        guessButton.setOnClickListener(v -> {
            String guessString = Objects.requireNonNull(guess.getText()).toString();

            if (guessString.isEmpty()) {
                Toast.makeText(EasyMode.this, "Harap masukan angka dengan rentang 1 - 100", Toast.LENGTH_LONG).show();
            } else {
                int guessNumber = Integer.parseInt(guessString);

                if (guessNumber == currentNumber.get()) {
                    currentStage.getAndIncrement();
                    currentAttempt.getAndIncrement();
                    numberAnswered.getAndIncrement();

                    AlertDialog.Builder dialog = new AlertDialog.Builder(EasyMode.this);
                    dialog.setTitle("Jawaban anda benar");
                    dialog.setMessage("Tebakan anda benar yaitu " + guessNumber + " dalam " + currentAttempt.get() + " percobaan");
                    dialog.setPositiveButton("Lanjut", (dialog1, which) -> {
                        if (currentAttempt.get() == 1) {
                            currentScore.addAndGet(5000);
                        } else if (currentAttempt.get() == 2) {
                            currentScore.addAndGet(4500);
                        } else if (currentAttempt.get() == 3) {
                            currentScore.addAndGet(4000);
                        } else if (currentAttempt.get() == 4) {
                            currentScore.addAndGet(3500);
                        } else if (currentAttempt.get() == 5) {
                            currentScore.addAndGet(3000);
                        } else if (currentAttempt.get() <= 10) {
                            currentScore.addAndGet(2000);
                        } else {
                            currentScore.addAndGet(1000);
                        }

                        if (currentStage.get() > 5) {
                            UserModel.setStaticScore(currentScore.get());
                            UserModel.setNumberAnswered(numberAnswered.get());

                            Intent intent = new Intent (EasyMode.this, FinalScore.class);
                            startActivity(intent);
                        } else {
                            currentAttempt.set(0);
                            currentNumber.set(NumberGenerator.easyNumberGenerator());
                            score.setText(String.valueOf(currentScore.get()));
                            stage.setText(getResources().getString(R.string.stage, currentStage.get()));
                            guess.setText("");
                            hint.setVisibility(View.INVISIBLE);

                            dialog1.dismiss();
                        }
                    });

                    AlertDialog dialogCreate = dialog.create();
                    dialogCreate.show();
                }
                else if (Math.abs(guessNumber - currentNumber.get()) <= 5 && guessNumber - currentNumber.get() < 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint1_1));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) <= 5 && guessNumber - currentNumber.get() > 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint1_2));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) <= 15 && guessNumber - currentNumber.get() < 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint2_1));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) <= 15 && guessNumber - currentNumber.get() > 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint2_2));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) > 15 && guessNumber - currentNumber.get() < 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint3_1));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) > 15 && guessNumber - currentNumber.get() > 0) {
                    currentAttempt.getAndIncrement();
                    hint.setText(getResources().getString(R.string.hint3_2));
                    hint.setTextColor(ContextCompat.getColor(EasyMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
