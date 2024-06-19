package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat

class TicketFoodDrinkDetail : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var foodName: TextView
    private lateinit var foodDate: TextView
    private lateinit var foodTotal: TextView
    private lateinit var foodPrice: TextView
    private lateinit var finishButton: Button

    private fun initComponents() {
        backButton = findViewById(R.id.backButtonETicketFoodDrink)
        foodName = findViewById(R.id.foodName)
        foodDate = findViewById(R.id.foodBuyDate)
        foodTotal = findViewById(R.id.foodTotal)
        foodPrice = findViewById(R.id.foodPrice)
        finishButton = findViewById(R.id.finishButtonETicketFoodDrink)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_food_drink_detail)

        initComponents()
        val databaseUst = DatabaseUst(this)

        foodName.text = databaseUst.getFoodName(UserIdModel.getTicketCode())
        foodDate.text = databaseUst.getFoodBuyDate(UserIdModel.getTicketCode())
        foodTotal.text = databaseUst.getFoodTotal(UserIdModel.getTicketCode()).toString()
        foodPrice.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getFoodPrice(UserIdModel.getTicketCode())))

        backButton.setOnClickListener {
            startActivity(Intent(this, Ticket::class.java))
        }

        finishButton.setOnClickListener {
            showPopUpFinishTicket()
        }
    }

    private fun showPopUpFinishTicket() {
        val dialog = AlertDialog.Builder(this)
        val databaseUst = DatabaseUst(this)

        dialog.setTitle("Tiket akan diselesaikan")
        dialog.setMessage("Setelah diselesaikan, tiket tidak akan bisa digunakan lagi. Apakah anda yakin ingin menyelesaikan tiket?")

        dialog.setPositiveButton("Ya") { dialog1, _ ->
            databaseUst.updateTicketFoodDrinkStatus(UserIdModel.getTicketCode(), "FINISH")
            Toast.makeText(this, "Tiket berhasil diselesaikan", Toast.LENGTH_SHORT).show()

            dialog1.dismiss()
            startActivity(Intent(this, Ticket::class.java))
        }
        dialog.setNegativeButton("No") {dialog2, _ -> dialog2.dismiss()}

        val dialogCreate = dialog.create()
        dialogCreate.show()
    }
}