package com.example.usta.util.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.example.usta.R
import com.example.usta.database.model.TicketListModel
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat
import com.example.usta.util.listener.SeatListListener

class AdapterTicketList(private val context: Context, private val listTicket: List<TicketListModel>)
    : ArrayAdapter<TicketListModel>(context, 0, listTicket) {
    private lateinit var trainName: TextView
    private lateinit var dateDeparture: TextView
    private lateinit var timeDeparture: TextView
    private lateinit var estTimeDeparture: TextView
    private lateinit var ticketPrice: TextView
    private lateinit var trainClass: TextView
    private lateinit var seatListArea: LinearLayout
    private var seatListListener: SeatListListener? = null

    private fun initComponents(view: View) {
        trainName = view.findViewById(R.id.trainNameTicketList)
        dateDeparture = view.findViewById(R.id.dateDepartureTicketList)
        timeDeparture = view.findViewById(R.id.timeDepartureTicketList)
        estTimeDeparture = view.findViewById(R.id.estTimeDepartureTicketList)
        ticketPrice = view.findViewById(R.id.ticketPriceTicketList)
        trainClass = view.findViewById(R.id.trainClassTicketList)
        seatListArea = view.findViewById(R.id.seatListAreaLayout)
    }

    fun setOnSeatListListener(seatListListener: SeatListListener) {
        this.seatListListener = seatListListener
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.listview_ticket_list_template, parent, false)
        val currentTicket: TicketListModel = listTicket[position]
        initComponents(view)

        val stringPrice: String = NumberingFormat.thousandSeparator(currentTicket.ticketPrice)

        trainName.text = currentTicket.trainName
        dateDeparture.text = currentTicket.departureDate
        timeDeparture.text = currentTicket.departureTime
        estTimeDeparture.text = currentTicket.estDepartureTime
        ticketPrice.text = context.getString(R.string.formatted_money_value, stringPrice)
        trainClass.text = currentTicket.trainClass

        seatListArea.setOnClickListener {
            UserIdModel.setDepartureId(currentTicket.departureId)

            seatListListener?.onSeatListListener(position)
        }

        return view
    }
}