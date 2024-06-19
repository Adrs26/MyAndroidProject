package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.StationModel
import com.example.usta.database.model.UserIdModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class TicketSearch : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var startStation: TextInputEditText
    private lateinit var finishStation: TextInputEditText
    private lateinit var nextButton: Button
    private lateinit var substButton: ImageButton
    private lateinit var addButton: ImageButton
    private lateinit var totalTicket: EditText

    private fun initComponents() {
        username = findViewById(R.id.usernameTicketSearch)
        startStation = findViewById<TextInputLayout>(R.id.startStationInputLayout).findViewById(R.id.startStationInputEditText)
        finishStation = findViewById<TextInputLayout>(R.id.finishStationInputLayout).findViewById(R.id.finishStationInputEditText)
        nextButton = findViewById(R.id.nextButton)
        substButton = findViewById(R.id.substTicket)
        addButton = findViewById(R.id.addTicket)
        totalTicket = findViewById(R.id.totalTicket)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_search)

        initComponents()
        val databaseUst = DatabaseUst(this)

        username.text = databaseUst.getUsername(UserIdModel.getId())

        var ticket = 1
        totalTicket.setText(ticket.toString())

        substButton.setOnClickListener {
            if (ticket > 1) {
                ticket--
                totalTicket.setText(ticket.toString())
            }
        }
        addButton.setOnClickListener {
            if (ticket < 4) {
                ticket++
                totalTicket.setText(ticket.toString())
            }
        }

        nextButton.setOnClickListener {
            val stringStartStation: String = startStation.text.toString().uppercase()
            val stringFinishStation: String = finishStation.text.toString().uppercase()

            when {
                stringStartStation.isEmpty() -> Toast.makeText(this, "Stasiun Awal tidak boleh kosong", Toast.LENGTH_SHORT).show()
                stringFinishStation.isEmpty() -> Toast.makeText(this, "Stasiun Tujuan tidak boleh kosong", Toast.LENGTH_SHORT).show()
                else -> {
                    StationModel.setStartStation(stringStartStation)
                    StationModel.setFinishStation(stringFinishStation)
                    UserIdModel.setTotalTicket(ticket)
                    startActivity(Intent(this, TicketList::class.java))
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            startActivity(Intent(this, Home::class.java))
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }
}