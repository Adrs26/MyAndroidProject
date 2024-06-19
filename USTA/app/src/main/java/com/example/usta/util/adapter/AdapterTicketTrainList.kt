package com.example.usta.util.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.usta.R
import com.example.usta.TicketTrainDetail
import com.example.usta.database.model.TicketTrainListModel
import com.example.usta.database.model.UserIdModel

class AdapterTicketTrainList(private val context: Context, private val listTicket: List<TicketTrainListModel>)
    : ArrayAdapter<TicketTrainListModel>(context, 0, listTicket) {
    private lateinit var trainName: TextView
    private lateinit var dateDeparture: TextView
    private lateinit var timeDeparture: TextView
    private lateinit var estTimeDeparture: TextView
    private lateinit var trainClass: TextView
    private lateinit var ticketTrainArea: LinearLayout

    private fun initComponents(view: View) {
        trainName = view.findViewById(R.id.trainNameTicketTrainList)
        dateDeparture = view.findViewById(R.id.dateDepartureTicketTrainList)
        timeDeparture = view.findViewById(R.id.timeDepartureTicketTrainList)
        estTimeDeparture = view.findViewById(R.id.estTimeDepartureTicketTrainList)
        trainClass = view.findViewById(R.id.trainClassTicketTrainList)
        ticketTrainArea = view.findViewById(R.id.ticketTrainListAreaLayout)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.listview_ticket_train_list_template, parent, false)
        val currentTicket: TicketTrainListModel = listTicket[position]

        initComponents(view)

        trainName.text = currentTicket.trainName
        dateDeparture.text = currentTicket.departureDate
        timeDeparture.text = currentTicket.departureTime
        estTimeDeparture.text = currentTicket.estDepartureTime
        trainClass.text = context.resources.getString(R.string.ticket_class_seat, currentTicket.seatCode, currentTicket.trainClass)

        ticketTrainArea.setOnClickListener {
            UserIdModel.setTicketCode(currentTicket.ticketCode)

            context.startActivity(Intent(context, TicketTrainDetail::class.java))
        }

        return view
    }
}