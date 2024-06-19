package com.example.usta.util

object SeatNumber {
    fun getSeatNumber(seatCode: String): Int{
        return when (seatCode) {
            "A1" -> 9
            "A2" -> 10
            "A3" -> 11
            "A4" -> 12
            "B1" -> 13
            "B2" -> 14
            "B3" -> 15
            "B4" -> 16
            "C1" -> 17
            "C2" -> 18
            "C3" -> 19
            "C4" -> 20
            "D1" -> 21
            "D2" -> 22
            "D3" -> 23
            "D4" -> 24
            "E1" -> 25
            "E2" -> 26
            "E3" -> 27
            "E4" -> 28
            "F1" -> 29
            "F2" -> 30
            "F3" -> 31
            "F4" -> 32
            else -> 99
        }
    }
}