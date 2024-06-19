package com.example.usta.database.model

object FoodDrinkPayModel {
    private var foodDrinkId: Int = 0
    private var foodDrinkName: String = "Makanan"
    private var foodDrinkTotal: Int = 0
    private var foodDrinkPrice: Int = 0

    fun setFoodDrinkId(id: Int) {
        this.foodDrinkId = id
    }

    fun getFoodDrinkId(): Int {
        return this.foodDrinkId
    }

    fun setFoodDrinkName(name: String) {
        this.foodDrinkName = name
    }

    fun getFoodDrinkName(): String {
        return this.foodDrinkName
    }

    fun setFoodDrinkTotal(total: Int) {
        this.foodDrinkTotal = total
    }

    fun getFoodDrinkTotal(): Int {
        return this.foodDrinkTotal
    }

    fun setFoodDrinkPrice(price: Int) {
        this.foodDrinkPrice = price
    }

    fun getFoodDrinkPrice(): Int {
        return this.foodDrinkPrice
    }
}