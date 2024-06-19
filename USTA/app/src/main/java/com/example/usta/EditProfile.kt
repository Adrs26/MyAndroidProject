package com.example.usta

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.google.android.material.textfield.TextInputEditText

class EditProfile : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var fullName: TextView
    private lateinit var number: TextView
    private lateinit var backButton: ImageButton
    private lateinit var saveButton: Button
    private var newName: String = "-"
    private var newNumber: String = "-"

    private fun initComponents() {
        username = findViewById(R.id.usernameEditProfile)
        fullName = findViewById(R.id.fullNameEditProfile)
        number = findViewById(R.id.numberEditProfile)
        backButton = findViewById(R.id.backButtonEditProfile)
        saveButton = findViewById(R.id.saveButtonEditProfile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        initComponents()
        val databaseUst = DatabaseUst(this)

        username.text = databaseUst.getUsername(UserIdModel.getId())
        fullName.text = databaseUst.getUserFullName(UserIdModel.getId())

        if (databaseUst.getUserNumber(UserIdModel.getId()) != "-") {
            number.text = databaseUst.getUserNumber(UserIdModel.getId())
        }

        fullName.setOnClickListener {
            showPopUpFullName()
        }
        number.setOnClickListener {
            showPopUpNumber()
        }

        backButton.setOnClickListener {
            finish()
        }
        saveButton.setOnClickListener {
            if (newName != "-" && newNumber != "-") {
                databaseUst.updateUserFullName(UserIdModel.getId(), newName)
                databaseUst.updateUserNumber(UserIdModel.getId(), newNumber)
                Toast.makeText(this, "Nama Lengkap berhasil diperbarui", Toast.LENGTH_SHORT).show()
                Toast.makeText(this, "Nomor Telepon berhasil diperbarui", Toast.LENGTH_SHORT).show()
            } else if (newName != "-") {
                databaseUst.updateUserFullName(UserIdModel.getId(), newName)
                Toast.makeText(this, "Nama Lengkap berhasil diperbarui", Toast.LENGTH_SHORT).show()
            } else if (newNumber != "-") {
                databaseUst.updateUserNumber(UserIdModel.getId(), newNumber)
                Toast.makeText(this, "Nomor Telepon berhasil diperbarui", Toast.LENGTH_SHORT).show()
            }

            finish()
        }
    }

    private fun showPopUpFullName() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_username_ticket_confirm, null)
        builder.setView(view)

        val fullName: TextInputEditText = view.findViewById<CardView>(R.id.namePopUpCardView).findViewById(R.id.namePopUpInputEditText)
        val cancelButton: Button = view.findViewById(R.id.cancelButtonPopUpName)
        val okButton: Button = view.findViewById(R.id.okButtonPopUpName)

        val databaseUst = DatabaseUst(this)
        val dialog = builder.create()

        fullName.setText(databaseUst.getUserFullName(UserIdModel.getId()))

        okButton.setOnClickListener {
            this.fullName.text = fullName.text.toString()
            newName = fullName.text.toString()
            dialog.dismiss()
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showPopUpNumber() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_number_ticket_confirm, null)
        builder.setView(view)

        val number: TextInputEditText = view.findViewById<CardView>(R.id.numberCardViewPopUp).findViewById(R.id.numberInputEditTextPopUp)
        val cancelButton: Button = view.findViewById(R.id.cancelButtonPopUpNumber)
        val okButton: Button = view.findViewById(R.id.okButtonPopUpNumber)

        val databaseUst = DatabaseUst(this)
        val dialog = builder.create()

        if (databaseUst.getUserNumber(UserIdModel.getId()) != "-") {
            number.setText(databaseUst.getUserNumber(UserIdModel.getId()))
        }

        okButton.setOnClickListener {
            if (number.text.toString().length < 11) {
                Toast.makeText(this, "Nomor telepon harus terdiri dari minimal 11 angka", Toast.LENGTH_SHORT).show()
            } else {
                this.number.text = number.text.toString()
                newNumber = number.text.toString()
                dialog.dismiss()
            }
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }
}