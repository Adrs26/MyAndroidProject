package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.usta.database.model.FoodDrinkModel
import com.example.usta.util.adapter.AdapterFoodDrinkList

class FoodMenu : AppCompatActivity() {
    private lateinit var foodButton: Button
    private lateinit var drinkButton: Button
    private lateinit var foodDrinkListView: ListView
    private lateinit var adapterFoodDrinkList: AdapterFoodDrinkList

    private fun initComponents() {
        foodButton = findViewById(R.id.foodButton)
        drinkButton = findViewById(R.id.drinkButton)
        foodDrinkListView = findViewById(R.id.foodDrinkListView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_menu)

        initComponents()
        adapterFoodDrinkList = AdapterFoodDrinkList(this, getFoodArray())
        foodDrinkListView.adapter = adapterFoodDrinkList

        foodButton.setOnClickListener {
            foodButton.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            drinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            adapterFoodDrinkList = AdapterFoodDrinkList(this, getFoodArray())
            foodDrinkListView.adapter = adapterFoodDrinkList
        }
        drinkButton.setOnClickListener {
            foodButton.setBackgroundColor(ContextCompat.getColor(this, R.color.white))
            drinkButton.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))

            adapterFoodDrinkList = AdapterFoodDrinkList(this, getDrinkArray())
            foodDrinkListView.adapter = adapterFoodDrinkList
        }
    }

    private fun getFoodArray(): ArrayList<FoodDrinkModel> {
        val foodArray = ArrayList<FoodDrinkModel>()
        foodArray.add(FoodDrinkModel(1, "Nasi Goreng", 1, 35000))
        foodArray.add(FoodDrinkModel(2, "Mie Goreng", 1, 35000))
        foodArray.add(FoodDrinkModel(3, "Nasi Rendang", 1, 50000))
        foodArray.add(FoodDrinkModel(4, "Nasi Ayam Bakar", 1, 45000))

        return foodArray
    }

    private fun getDrinkArray(): ArrayList<FoodDrinkModel> {
        val drinkArray = ArrayList<FoodDrinkModel>()
        drinkArray.add(FoodDrinkModel(5, "Teh (Es/Hangat)", 1, 10000))
        drinkArray.add(FoodDrinkModel(6, "Jus Alpukat", 1, 20000))
        drinkArray.add(FoodDrinkModel(7, "Es Campur", 1, 23000))
        drinkArray.add(FoodDrinkModel(8, "Air Mineral", 1, 6000))

        return drinkArray
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