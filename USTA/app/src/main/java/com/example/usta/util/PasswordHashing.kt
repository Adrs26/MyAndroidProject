package com.example.usta.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object PasswordHashing {
    fun hashPassword(password: String): String {
        return try {
            val digest = MessageDigest.getInstance("SHA-256")
            val encodedHash = digest.digest(password.toByteArray())
            val hexString = StringBuilder()
            for (b in encodedHash) {
                val hex = Integer.toHexString(0xff and b.toInt())
                if (hex.length == 1) {
                    hexString.append('0')
                }
                hexString.append(hex)
            }
            hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            "Failed Process"
        }
    }
}