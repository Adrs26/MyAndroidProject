package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel

class Profile : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var editProfile: Button
    private lateinit var history: Button
    private lateinit var logout: Button
    private lateinit var homeButton: ImageButton
    private lateinit var ticketButton: ImageButton

    private fun initComponents() {
        username = findViewById(R.id.usernameProfile)
        editProfile = findViewById(R.id.editProfileButton)
        history = findViewById(R.id.historyTicketButton)
        logout = findViewById(R.id.logoutButton)
        homeButton = findViewById(R.id.homeButtonProfile)
        ticketButton = findViewById(R.id.ticketButtonProfile)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initComponents()
        val databaseUst = DatabaseUst(this)

        username.text = databaseUst.getUsername(UserIdModel.getId())

        editProfile.setOnClickListener {
            startActivity(Intent(this, EditProfile::class.java))
        }
        history.setOnClickListener {
            startActivity(Intent(this, TicketHistory::class.java))
        }
        logout.setOnClickListener {
            showPopUpLogout()
        }

        homeButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
        ticketButton.setOnClickListener {
            startActivity(Intent(this, Ticket::class.java))
        }
    }

    private fun showPopUpLogout() {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Anda akan keluar")
        dialog.setMessage("Apakah anda yakin ingin keluar dari akun ini?")

        dialog.setPositiveButton("Ya") { dialog1, _ ->
            dialog1.dismiss()
            startActivity(Intent(this, Login::class.java))
        }
        dialog.setNegativeButton("Tidak") {dialog2, _ -> dialog2.dismiss()}

        val dialogCreate = dialog.create()
        dialogCreate.show()
    }
}