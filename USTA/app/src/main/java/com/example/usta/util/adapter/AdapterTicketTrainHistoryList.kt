package com.example.usta.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.usta.R
import com.example.usta.database.model.TicketTrainHistoryListModel

class AdapterTicketTrainHistoryList(private val context: Context, private val listTicket: List<TicketTrainHistoryListModel>)
    : ArrayAdapter<TicketTrainHistoryListModel>(context, 0, listTicket) {
    private lateinit var trainName: TextView
    private lateinit var dateDeparture: TextView
    private lateinit var timeDeparture: TextView
    private lateinit var estTimeDeparture: TextView
    private lateinit var trainClass: TextView
    private lateinit var ticketStatus: TextView

    private fun initComponents(view: View) {
        trainName = view.findViewById(R.id.trainNameTicketTrainListHistory)
        dateDeparture = view.findViewById(R.id.dateDepartureTicketTrainListHistory)
        timeDeparture = view.findViewById(R.id.timeDepartureTicketTrainListHistory)
        estTimeDeparture = view.findViewById(R.id.estTimeDepartureTicketTrainListHistory)
        trainClass = view.findViewById(R.id.trainClassTicketTrainListHistory)
        ticketStatus = view.findViewById(R.id.ticketTrainHistoryStatus)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.listview_ticket_train_list_history_template, parent, false)
        val currentTicket: TicketTrainHistoryListModel = listTicket[position]

        initComponents(view)

        trainName.text = currentTicket.trainName
        dateDeparture.text = currentTicket.departureDate
        timeDeparture.text = currentTicket.departureTime
        estTimeDeparture.text = currentTicket.estDepartureTime
        trainClass.text = context.resources.getString(R.string.ticket_class_seat, currentTicket.seatCode, currentTicket.trainClass)

        if (currentTicket.ticketStatus == "CANCEL") {
            ticketStatus.text = "Dibatalkan"
            ticketStatus.setTextColor(ContextCompat.getColor(context, R.color.red))
        } else {
            ticketStatus.text = "Selesai"
            ticketStatus.setTextColor(ContextCompat.getColor(context, R.color.green))
        }

        return view
    }
}