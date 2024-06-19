package com.example.usta

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.FoodDrinkPayModel
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat
import com.example.usta.util.PasswordHashing
import com.example.usta.util.RandomNumberGenerator
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class FoodDetail : AppCompatActivity() {
    private lateinit var foodDrinkImage: ImageView
    private lateinit var foodDrinkName: TextView
    private lateinit var foodDrinkTotal: EditText
    private lateinit var ustaPay: TextView
    private lateinit var topUpButton: ImageButton
    private lateinit var foodDrinkPrice: TextView
    private lateinit var buyButton: Button

    private fun initComponents() {
        foodDrinkImage = findViewById(R.id.foodDrinkImageDetail)
        foodDrinkName = findViewById(R.id.foodDrinkNameDetail)
        foodDrinkTotal = findViewById(R.id.foodDrinkTotalDetail)
        ustaPay = findViewById(R.id.ustaPayAmountDetail)
        topUpButton = findViewById(R.id.topUpButtonFoodDetail)
        foodDrinkPrice = findViewById(R.id.foodDrinkPriceDetail)
        buyButton = findViewById(R.id.buyButtonFoodDetail)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_detail)

        initComponents()
        val databaseUst = DatabaseUst(this)

        when (FoodDrinkPayModel.getFoodDrinkId()) {
            1 -> foodDrinkImage.setImageResource(R.drawable.nasi_goreng)
            2 -> foodDrinkImage.setImageResource(R.drawable.mie_goreng)
            3 -> foodDrinkImage.setImageResource(R.drawable.nasi_rendang)
            4 -> foodDrinkImage.setImageResource(R.drawable.nasi_ayam_bakar)
            5 -> foodDrinkImage.setImageResource(R.drawable.teh)
            6 -> foodDrinkImage.setImageResource(R.drawable.jus_alpukat)
            7 -> foodDrinkImage.setImageResource(R.drawable.es_campur)
            8 -> foodDrinkImage.setImageResource(R.drawable.air_mineral)
        }

        foodDrinkName.text = FoodDrinkPayModel.getFoodDrinkName()
        foodDrinkTotal.setText(FoodDrinkPayModel.getFoodDrinkTotal().toString())
        ustaPay.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getUserUstaPay(UserIdModel.getId())))
        foodDrinkPrice.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(FoodDrinkPayModel.getFoodDrinkPrice()))

        topUpButton.setOnClickListener {
            showPopUpTopUp()
        }

        buyButton.setOnClickListener {
            when {
                databaseUst.getUserUstaPay(UserIdModel.getId()) < FoodDrinkPayModel.getFoodDrinkPrice() -> Toast.makeText(this, "Saldo UstaPay anda tidak cukup", Toast.LENGTH_SHORT).show()
                else -> {
                    databaseUst.updateUserUstaPay(UserIdModel.getId(), databaseUst.getUserUstaPay(UserIdModel.getId()), -FoodDrinkPayModel.getFoodDrinkPrice())
                    databaseUst.insertFoodDrinkData(RandomNumberGenerator.getRandomNumber().toString(), UserIdModel.getId(), FoodDrinkPayModel.getFoodDrinkName(), FoodDrinkPayModel.getFoodDrinkTotal(), FoodDrinkPayModel.getFoodDrinkPrice(), getTodayDateFormat())
                    Toast.makeText(this, "Pembelian berhasil, tiket makanan bisa dicek pada menu tiket", Toast.LENGTH_SHORT).show()

                    startActivity(Intent(this, Home::class.java))
                }
            }
        }
    }

    private fun showPopUpTopUp() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_top_up_ustapay, null)
        builder.setView(view)

        val amount: TextInputEditText = view.findViewById<CardView>(R.id.amountCardView).findViewById(R.id.amountInputEditText)
        val password: TextInputEditText = view.findViewById<CardView>(R.id.passwordCardView).findViewById(R.id.passwordInputEditText)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)
        val okButton: Button = view.findViewById(R.id.okButton)

        val dialog = builder.create()

        okButton.setOnClickListener {
            val databaseUst = DatabaseUst(this)

            val intAmount = amount.text.toString().toInt()
            val hashPassword = PasswordHashing.hashPassword(password.text.toString())

            when {
                hashPassword != databaseUst.getUserPassword(UserIdModel.getId()) -> Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show()
                intAmount + databaseUst.getUserUstaPay(UserIdModel.getId()) > 9999999 -> Toast.makeText(this, "Saldo maksimal UstaPay adalah Rp 9.999.999", Toast.LENGTH_SHORT).show()
                else -> {
                    databaseUst.updateUserUstaPay(UserIdModel.getId(), databaseUst.getUserUstaPay(UserIdModel.getId()), intAmount)
                    Toast.makeText(this, "Top Up UstaPay berhasil", Toast.LENGTH_SHORT).show()
                    ustaPay.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getUserUstaPay(UserIdModel.getId())))
                    databaseUst.close()
                    dialog.dismiss()
                }
            }
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun getTodayDateFormat(): String {
        val today: LocalDate = LocalDate.now()
        val day: Int = today.dayOfMonth
        val month: String = today.month.getDisplayName(TextStyle.FULL, Locale("id", "ID"))
        val year: Int = today.year

        return resources.getString(R.string.today_date, day, month, year)
    }
}