package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.PasswordHashing
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUp : AppCompatActivity() {
    private lateinit var fullName: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirm: TextInputEditText
    private lateinit var signUpButton: Button

    private fun initComponents() {
        fullName = findViewById<TextInputLayout>(R.id.fullNameSignUpInputLayout).findViewById(R.id.fullNameSignUpInputEditText)
        username = findViewById<TextInputLayout>(R.id.usernameSignUpInputLayout).findViewById(R.id.usernameSignUpInputEditText)
        password = findViewById<TextInputLayout>(R.id.passwordSignUpInputLayout).findViewById(R.id.passwordSignUpInputEditText)
        confirm = findViewById<TextInputLayout>(R.id.confirmPasswordSignUpInputLayout).findViewById(R.id.confirmPasswordSignUpInputEditText)
        signUpButton = findViewById(R.id.signUpButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        initComponents()
        val databaseUst = DatabaseUst(this)

        signUpButton.setOnClickListener {
            val stringFullName: String = fullName.text.toString()
            val stringUsername: String = username.text.toString()
            val stringPassword: String = password.text.toString()
            val stringConfirm: String = confirm.text.toString()

            when {
                stringFullName.isEmpty() -> Toast.makeText(this, "Nama lengkap tidak boleh kosong", Toast.LENGTH_SHORT).show()
                stringUsername.isEmpty() -> Toast.makeText(this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show()
                stringPassword.isEmpty() -> Toast.makeText(this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                else -> {
                    val hashedPassword: String = PasswordHashing.hashPassword(stringPassword)

                    if (stringPassword == stringConfirm) {
                        if (databaseUst.signUpAccount(stringFullName, stringUsername, hashedPassword, 0, "-")) {
                            UserIdModel.setId(databaseUst.getUserId(stringUsername))
                            startActivity(Intent(this, Home::class.java))
                        } else {
                            Toast.makeText(this, "Username telah dipakai", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Harap konfirmasi password dengan benar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        databaseUst.close()
    }
}