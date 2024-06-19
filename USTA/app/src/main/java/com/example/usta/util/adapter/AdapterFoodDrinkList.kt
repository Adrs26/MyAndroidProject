package com.example.usta.util.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.usta.FoodDetail
import com.example.usta.R
import com.example.usta.database.model.FoodDrinkModel
import com.example.usta.database.model.FoodDrinkPayModel
import com.example.usta.util.NumberingFormat

class AdapterFoodDrinkList(private val context: Context, private val listFoodDrink: MutableList<FoodDrinkModel>)
    : ArrayAdapter<FoodDrinkModel>(context, 0, listFoodDrink) {

    private class ViewHolder(view: View) {
        val foodDrinkImage: ImageView = view.findViewById(R.id.foodDrinkImage)
        val foodDrinkName: TextView = view.findViewById(R.id.foodDrinkName)
        val foodDrinkPrice: TextView = view.findViewById(R.id.foodDrinkPrice)
        val substButton: ImageButton = view.findViewById(R.id.substFoodDrink)
        val foodDrinkTotal: EditText = view.findViewById(R.id.foodDrinkTotal)
        val addButton: ImageButton = view.findViewById(R.id.addFoodDrink)
        val buyButton: Button = view.findViewById(R.id.buyButton)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_food_drink_template, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val currentFoodDrink: FoodDrinkModel = listFoodDrink[position]
        val stringPrice: String = NumberingFormat.thousandSeparator(currentFoodDrink.foodDrinkPrice)

        when (currentFoodDrink.foodDrinkId) {
            1 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.nasi_goreng)
            2 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.mie_goreng)
            3 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.nasi_rendang)
            4 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.nasi_ayam_bakar)
            5 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.teh)
            6 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.jus_alpukat)
            7 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.es_campur)
            8 -> viewHolder.foodDrinkImage.setImageResource(R.drawable.air_mineral)
        }

        viewHolder.foodDrinkName.text = currentFoodDrink.foodDrinkName
        viewHolder.foodDrinkPrice.text = context.resources.getString(R.string.formatted_money_value, stringPrice)

        var total = currentFoodDrink.foodDrinkTotal
        viewHolder.foodDrinkTotal.setText(total.toString())

        viewHolder.substButton.setOnClickListener {
            if (total > 1) {
                total--
                viewHolder.foodDrinkTotal.setText(total.toString())
                currentFoodDrink.foodDrinkTotal = total
            }
        }

        viewHolder.addButton.setOnClickListener {
            if (total < 4) {
                total++
                viewHolder.foodDrinkTotal.setText(total.toString())
                currentFoodDrink.foodDrinkTotal = total
            }
        }

        viewHolder.buyButton.setOnClickListener {
            FoodDrinkPayModel.setFoodDrinkId(currentFoodDrink.foodDrinkId)
            FoodDrinkPayModel.setFoodDrinkName(currentFoodDrink.foodDrinkName)
            FoodDrinkPayModel.setFoodDrinkTotal(total)
            FoodDrinkPayModel.setFoodDrinkPrice(currentFoodDrink.foodDrinkPrice * total)

            context.startActivity(Intent(context, FoodDetail::class.java))
        }

        return view
    }
}