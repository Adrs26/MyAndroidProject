package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.StationModel
import com.example.usta.util.adapter.AdapterTicketList
import com.example.usta.util.listener.SeatListListener

class TicketList : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var stationList: TextView
    private lateinit var ticketListView: ListView
    private lateinit var adapterTicketList: AdapterTicketList

    private fun initComponents() {
        backButton = findViewById(R.id.backButton)
        stationList = findViewById(R.id.stationListTextView)
        ticketListView = findViewById(R.id.listTicketListView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_list)

        initComponents()
        val databaseUst = DatabaseUst(this)

        stationList.text = resources.getString(R.string.station_list, StationModel.getStartStation(), StationModel.getFinishStation())

        adapterTicketList = AdapterTicketList(this, databaseUst.selectTicketList(StationModel.getStartStation(), StationModel.getFinishStation()))
        ticketListView.adapter = adapterTicketList
        adapterTicketList.setOnSeatListListener(object : SeatListListener {
            override fun onSeatListListener(position: Int) {
                startActivity(Intent(this@TicketList, Seat::class.java))
            }
        })

        backButton.setOnClickListener {
            startActivity(Intent(this, TicketSearch::class.java))
        }
    }
}