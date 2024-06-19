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

public class HardMode extends AppCompatActivity {
    private TextView score;
    private TextView guessChance;
    private TextView stage;
    private TextInputEditText guess;
    private TextView hint;
    private Button guessButton;

    private void initComponents() {
        score = findViewById(R.id.hardScore);
        guessChance = findViewById(R.id.hardGuessChance);
        stage = findViewById(R.id.hardStage);
        guess = findViewById(R.id.hardInputLayout).findViewById(R.id.hardInputEditText);
        hint = findViewById(R.id.hardHint);
        guessButton = findViewById(R.id.hardGuessButton);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_mode);

        initComponents();

        AtomicInteger currentScore = new AtomicInteger(0);
        AtomicInteger currentGuessChance = new AtomicInteger(5);
        AtomicInteger currentStage = new AtomicInteger(1);
        AtomicInteger currentAttempt = new AtomicInteger(0);
        AtomicInteger numberAnswered = new AtomicInteger(0);
        AtomicInteger currentNumber = new AtomicInteger(NumberGenerator.hardNumberGenerator());

        score.setText(String.valueOf(currentScore.get()));
        guessChance.setText(String.valueOf(currentGuessChance.get()));
        stage.setText(getResources().getString(R.string.stage, currentStage.get()));
        hint.setVisibility(View.INVISIBLE);

        guessButton.setOnClickListener(v -> {
            String guessString = Objects.requireNonNull(guess.getText()).toString();

            if (guessString.isEmpty()) {
                Toast.makeText(HardMode.this, "Harap masukan angka dengan rentang 1 - 200", Toast.LENGTH_LONG).show();
            } else {
                int guessNumber = Integer.parseInt(guessString);

                if (guessNumber == currentNumber.get()) {
                    currentStage.getAndIncrement();
                    currentAttempt.getAndIncrement();
                    numberAnswered.getAndIncrement();

                    AlertDialog.Builder dialog = new AlertDialog.Builder(HardMode.this);
                    dialog.setTitle("Jawaban anda benar");
                    dialog.setMessage("Tebakan anda benar yaitu " + guessNumber + " dalam " + currentAttempt.get() + " percobaan");
                    dialog.setPositiveButton("Lanjut", (dialog1, which) -> {
                        if (currentAttempt.get() == 1) {
                            currentScore.addAndGet(7000);
                        } else if (currentAttempt.get() == 2) {
                            currentScore.addAndGet(6500);
                        } else if (currentAttempt.get() == 3) {
                            currentScore.addAndGet(6000);
                        } else if (currentAttempt.get() == 4) {
                            currentScore.addAndGet(5500);
                        } else if (currentAttempt.get() == 5) {
                            currentScore.addAndGet(5000);
                        }

                        if (currentStage.get() > 5) {
                            UserModel.setStaticScore(currentScore.get());
                            UserModel.setNumberAnswered(numberAnswered.get());

                            Intent intent = new Intent(HardMode.this, FinalScore.class);
                            startActivity(intent);
                        } else {
                            currentAttempt.set(0);
                            currentGuessChance.set(5);
                            currentNumber.set(NumberGenerator.hardNumberGenerator());
                            score.setText(String.valueOf(currentScore.get()));
                            guessChance.setText(String.valueOf(currentGuessChance.get()));
                            stage.setText(getResources().getString(R.string.stage, currentStage.get()));
                            guess.setText("");
                            hint.setVisibility(View.INVISIBLE);

                            dialog1.dismiss();
                        }
                    });

                    AlertDialog dialogCreate = dialog.create();
                    dialogCreate.show();
                } else if (Math.abs(guessNumber - currentNumber.get()) <= 15 && guessNumber - currentNumber.get() < 0 && currentGuessChance.get() > 1) {
                    currentAttempt.getAndIncrement();
                    currentGuessChance.getAndDecrement();
                    guessChance.setText(String.valueOf(currentGuessChance.get()));
                    hint.setText(getResources().getString(R.string.hint2_1));
                    hint.setTextColor(ContextCompat.getColor(HardMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) <= 15 && guessNumber - currentNumber.get() > 0 && currentGuessChance.get() > 1) {
                    currentAttempt.getAndIncrement();
                    currentGuessChance.getAndDecrement();
                    guessChance.setText(String.valueOf(currentGuessChance.get()));
                    hint.setText(getResources().getString(R.string.hint2_2));
                    hint.setTextColor(ContextCompat.getColor(HardMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) > 15 && guessNumber - currentNumber.get() < 0 && currentGuessChance.get() > 1) {
                    currentAttempt.getAndIncrement();
                    currentGuessChance.getAndDecrement();
                    guessChance.setText(String.valueOf(currentGuessChance.get()));
                    hint.setText(getResources().getString(R.string.hint3_1));
                    hint.setTextColor(ContextCompat.getColor(HardMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (Math.abs(guessNumber - currentNumber.get()) > 15 && guessNumber - currentNumber.get() > 0 && currentGuessChance.get() > 1) {
                    currentAttempt.getAndIncrement();
                    currentGuessChance.getAndDecrement();
                    guessChance.setText(String.valueOf(currentGuessChance.get()));
                    hint.setText(getResources().getString(R.string.hint3_2));
                    hint.setTextColor(ContextCompat.getColor(HardMode.this, R.color.black));
                    hint.setVisibility(View.VISIBLE);
                } else if (currentGuessChance.get() == 1) {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(HardMode.this);
                    dialog.setTitle("Kesempatan menebak telah habis");
                    dialog.setMessage("Angka tersebut adalah " + currentNumber.get());
                    dialog.setPositiveButton("Lanjut", (dialog1, which) -> {
                        currentStage.getAndIncrement();

                        if (currentStage.get() > 5) {
                            UserModel.setStaticScore(currentScore.get());
                            UserModel.setNumberAnswered(numberAnswered.get());

                            Intent intent = new Intent(HardMode.this, FinalScore.class);
                            startActivity(intent);
                        } else {
                            currentAttempt.set(0);
                            currentGuessChance.set(5);
                            currentScore.addAndGet(0);
                            currentNumber.set(NumberGenerator.hardNumberGenerator());
                            score.setText(String.valueOf(currentScore.get()));
                            guessChance.setText(String.valueOf(currentGuessChance.get()));
                            stage.setText(getResources().getString(R.string.stage, currentStage.get()));
                            guess.setText("");
                            hint.setVisibility(View.INVISIBLE);

                            dialog1.dismiss();
                        }
                    });

                    AlertDialog dialogCreate = dialog.create();
                    dialogCreate.show();
                }
            }
        });
    }
}
