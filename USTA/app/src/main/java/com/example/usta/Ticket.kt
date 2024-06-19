package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.adapter.AdapterTicketFoodDrinkList
import com.example.usta.util.adapter.AdapterTicketTrainList

class Ticket : AppCompatActivity() {
    private lateinit var ticketTrainButton: Button
    private lateinit var ticketFoodDrinkButton: Button
    private lateinit var ticketList: ListView
    private lateinit var adapterTicketTrainList: AdapterTicketTrainList
    private lateinit var adapterTicketFoodDrinkListModel: AdapterTicketFoodDrinkList
    private lateinit var homeButton: ImageButton
    private lateinit var profileButton: ImageButton

    private fun initComponents() {
        ticketTrainButton = findViewById(R.id.ticketTrainButton)
        ticketFoodDrinkButton = findViewById(R.id.ticketFoodDrinkButton)
        ticketList = findViewById(R.id.ticketTrainListView)
        homeButton = findViewById(R.id.homeButtonTicket)
        profileButton = findViewById(R.id.profileButtonTicket)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        initComponents()
        val databaseUst = DatabaseUst(this)

        adapterTicketTrainList = AdapterTicketTrainList(this, databaseUst.selectTicketTrainData(UserIdModel.getId(), "WAITING"))
        ticketList.adapter = adapterTicketTrainList

        ticketTrainButton.setOnClickListener {
            ticketTrainButton.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            ticketFoodDrinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            adapterTicketTrainList = AdapterTicketTrainList(this, databaseUst.selectTicketTrainData(UserIdModel.getId(), "WAITING"))
            ticketList.adapter = adapterTicketTrainList
        }
        ticketFoodDrinkButton.setOnClickListener {
            ticketTrainButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            ticketFoodDrinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))

            adapterTicketFoodDrinkListModel = AdapterTicketFoodDrinkList(this, databaseUst.selectTicketFoodDrinkData(UserIdModel.getId(), "WAITING"))
            ticketList.adapter = adapterTicketFoodDrinkListModel
        }

        homeButton.setOnClickListener {
            startActivity(Intent(this, Home::class.java))
        }
        profileButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
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