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
import com.example.usta.TicketFoodDrinkDetail
import com.example.usta.TicketTrainDetail
import com.example.usta.database.model.TicketFoodDrinkListModel
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat

class AdapterTicketFoodDrinkList(private val context: Context, private val listTicket: List<TicketFoodDrinkListModel>)
    : ArrayAdapter<TicketFoodDrinkListModel>(context, 0, listTicket) {
    private lateinit var foodName: TextView
    private lateinit var foodTotal: TextView
    private lateinit var foodPrice: TextView
    private lateinit var ticketFoodDrinkArea: LinearLayout

    private fun initComponents(view: View) {
        foodName = view.findViewById(R.id.foodDrinkNameWaitingList)
        foodTotal = view.findViewById(R.id.foodDrinkTotalWaitingList)
        foodPrice = view.findViewById(R.id.foodDrinkPriceWaitingList)
        ticketFoodDrinkArea = view.findViewById(R.id.ticketFoodDrinkListAreaLayout)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.listview_ticket_food_drink_list_template, parent, false)
        val currentTicket: TicketFoodDrinkListModel = listTicket[position]

        initComponents(view)

        foodName.text = currentTicket.foodDrinkName
        foodTotal.text = currentTicket.foodDrinkTotal.toString()
        foodPrice.text = context.resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(currentTicket.foodDrinkPrice))

        ticketFoodDrinkArea.setOnClickListener {
            UserIdModel.setTicketCode(currentTicket.foodDrinkCode)

            context.startActivity(Intent(context, TicketFoodDrinkDetail::class.java))
        }

        return view
    }
}