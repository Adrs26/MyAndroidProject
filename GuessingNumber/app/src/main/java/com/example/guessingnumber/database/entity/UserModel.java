package com.example.guessingnumber.database.entity;

public class UserModel {
    private final String username;
    private final String difficulty;
    private final int score;
    private final int ranking;
    private static int numberAnswered;
    private static String staticUsername;
    private static String staticDifficulty;
    private static int staticScore;

    public UserModel(String username, String difficulty, int userScore) {
        this.username = username;
        this.difficulty = difficulty;
        this.score = userScore;
        this.ranking = 0;
    }

    public UserModel(String username, String difficulty, int userScore, int ranking) {
        this.username = username;
        this.difficulty = difficulty;
        this.score = userScore;
        this.ranking = ranking;
    }

    public String getUsername() {
        return username;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getScore() {
        return score;
    }

    public int getRanking() {
        return ranking;
    }

    public static int getNumberAnswered() {
        return numberAnswered;
    }

    public static void setNumberAnswered(int numberAnswered) {
        UserModel.numberAnswered = numberAnswered;
    }

    public static String getStaticUsername() {
        return staticUsername;
    }

    public static void setStaticUsername(String staticUsername) {
        UserModel.staticUsername = staticUsername;
    }

    public static String getStaticDifficulty() {
        return staticDifficulty;
    }

    public static void setStaticDifficulty(String staticDifficulty) {
        UserModel.staticDifficulty = staticDifficulty;
    }

    public static int getStaticScore() {
        return staticScore;
    }

    public static void setStaticScore(int staticScore) {
        UserModel.staticScore = staticScore;
    }
}
