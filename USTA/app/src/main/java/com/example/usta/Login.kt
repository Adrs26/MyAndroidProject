package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.PasswordHashing
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class Login : AppCompatActivity() {
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var loginButton: Button
    private lateinit var createAccount: TextView

    private fun initComponents() {
        username = findViewById<TextInputLayout>(R.id.usernameLoginInputLayout).findViewById(R.id.usernameLoginInputEditText)
        password = findViewById<TextInputLayout>(R.id.passwordLoginInputLayout).findViewById(R.id.passwordLoginInputEditText)
        loginButton = findViewById(R.id.loginButton)
        createAccount = findViewById(R.id.createAccount)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initComponents()
        val databaseUst = DatabaseUst(this)

        loginButton.setOnClickListener {
            val stringUsername: String = username.text.toString()
            val stringPassword: String = password.text.toString()

            when {
                stringUsername.isEmpty() -> Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
                stringPassword.isEmpty() -> Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                else -> {
                    val hashedPassword: String = PasswordHashing.hashPassword(stringPassword)

                    if (databaseUst.loginAccount(stringUsername, hashedPassword)) {
                        UserIdModel.setId(databaseUst.getUserId(stringUsername))
                        startActivity(Intent(this, Home::class.java))
                    }
                }
            }
        }

        createAccount.setOnClickListener {
            startActivity(Intent(this, SignUp::class.java))
        }

        databaseUst.close()
    }
}
