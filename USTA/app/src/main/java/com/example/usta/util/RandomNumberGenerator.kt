package com.example.usta.util

import kotlin.random.Random

object RandomNumberGenerator {
    fun getRandomNumber(): Int {
        return Random.nextInt(10_000_000, 100_000_000)
    }
}