package com.example.usta.util

import java.text.DecimalFormat

object NumberingFormat {
    fun thousandSeparator(number: Int): String {
        val decimalFormat = DecimalFormat("#,###.###")
        decimalFormat.isDecimalSeparatorAlwaysShown = false

        return decimalFormat.format(number).replace(",", ".")
    }
}