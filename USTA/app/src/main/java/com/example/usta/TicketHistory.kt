package com.example.usta

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.adapter.AdapterTicketFoodDrinkList
import com.example.usta.util.adapter.AdapterTicketTrainHistoryList

class TicketHistory : AppCompatActivity() {
    private lateinit var ticketTrainButton: Button
    private lateinit var ticketFoodDrinkButton: Button
    private lateinit var ticketList: ListView
    private lateinit var adapterTicketTrainHistoryList: AdapterTicketTrainHistoryList
    private lateinit var adapterTicketFoodDrinkList: AdapterTicketFoodDrinkList

    private fun initComponents() {
        ticketTrainButton = findViewById(R.id.ticketTrainHistoryButton)
        ticketFoodDrinkButton = findViewById(R.id.ticketFoodDrinkHistoryButton)
        ticketList = findViewById(R.id.ticketHistoryListView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_history)

        initComponents()
        val databaseUst = DatabaseUst(this)

        adapterTicketTrainHistoryList = AdapterTicketTrainHistoryList(this, databaseUst.selectTicketTrainDataHistory(UserIdModel.getId(), "FINISH", "CANCEL"))
        ticketList.adapter = adapterTicketTrainHistoryList

        ticketTrainButton.setOnClickListener {
            ticketTrainButton.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            ticketFoodDrinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            adapterTicketTrainHistoryList = AdapterTicketTrainHistoryList(this, databaseUst.selectTicketTrainDataHistory(UserIdModel.getId(), "FINISH", "CANCEL"))
            ticketList.adapter = adapterTicketTrainHistoryList
        }

        ticketFoodDrinkButton.setOnClickListener {
            ticketTrainButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            ticketFoodDrinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))

            adapterTicketFoodDrinkList = AdapterTicketFoodDrinkList(this, databaseUst.selectTicketFoodDrinkData(UserIdModel.getId(), "FINISH"))
            ticketList.adapter = adapterTicketFoodDrinkList
        }
    }
}