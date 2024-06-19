package com.example.guessingnumber.util;

import java.util.Random;

public class NumberGenerator {
    private static final Random random = new Random();

    public static int easyNumberGenerator() {
        return random.nextInt(100) + 1;
    }

    public static int normalNumberGenerator() {
        return random.nextInt(150) + 1;
    }

    public static int hardNumberGenerator() {
        return random.nextInt(200) + 1;
    }
}
