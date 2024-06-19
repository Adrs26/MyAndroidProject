package com.example.usta

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat
import com.example.usta.util.PasswordHashing
import com.google.android.material.textfield.TextInputEditText

class Home : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var ustapay: TextView
    private lateinit var ticketSearchArea: LinearLayout
    private lateinit var ticketSearchImage: ImageButton
    private lateinit var ticketSearchTextView: TextView
    private lateinit var sholatScheduleArea: LinearLayout
    private lateinit var sholatScheduleImage: ImageButton
    private lateinit var sholatScheduleTextView: TextView
    private lateinit var foodMenuArea: LinearLayout
    private lateinit var foodMenuImage: ImageButton
    private lateinit var foodMenuTextView: TextView
    private lateinit var ticketButton: ImageButton
    private lateinit var profileButton: ImageButton
    private lateinit var topUpButton: ImageButton

    private fun initComponents() {
        username = findViewById(R.id.usernameHome)
        ustapay = findViewById(R.id.ustaPayAmountHome)
        ticketSearchArea = findViewById(R.id.ticketSearchArea)
        ticketSearchImage = findViewById(R.id.ticketSearchImageButton)
        ticketSearchTextView = findViewById(R.id.ticketSearchTextView)
        sholatScheduleArea = findViewById(R.id.sholatScheduleArea)
        sholatScheduleImage = findViewById(R.id.sholatScheduleImageButton)
        sholatScheduleTextView = findViewById(R.id.sholatScheduleTextView)
        foodMenuArea = findViewById(R.id.foodMenuArea)
        foodMenuImage = findViewById(R.id.foodMenuImageButton)
        foodMenuTextView = findViewById(R.id.foodMenuTextView)
        ticketButton = findViewById(R.id.ticketButtonHome)
        profileButton = findViewById(R.id.profileButtonHome)
        topUpButton = findViewById(R.id.topUpButtonHome)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        initComponents()
        val databaseUst = DatabaseUst(this)

        username.text = databaseUst.getUsername(UserIdModel.getId())

        ustapay.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getUserUstaPay(UserIdModel.getId())))

        topUpButton.setOnClickListener {
            showPopUpTopUp()
        }

        ticketSearchArea.setOnClickListener {
            startActivity(Intent(this, TicketSearch::class.java))
        }
        ticketSearchImage.setOnClickListener {
            startActivity(Intent(this, TicketSearch::class.java))
        }
        ticketSearchTextView.setOnClickListener {
            startActivity(Intent(this, TicketSearch::class.java))
        }
        sholatScheduleArea.setOnClickListener {
            startActivity(Intent(this, SholatSchedule::class.java))
        }
        sholatScheduleImage.setOnClickListener {
            startActivity(Intent(this, SholatSchedule::class.java))
        }
        sholatScheduleTextView.setOnClickListener {
            startActivity(Intent(this, SholatSchedule::class.java))
        }
        foodMenuArea.setOnClickListener {
            startActivity(Intent(this, FoodMenu::class.java))
        }
        foodMenuImage.setOnClickListener {
            startActivity(Intent(this, FoodMenu::class.java))
        }
        foodMenuTextView.setOnClickListener {
            startActivity(Intent(this, FoodMenu::class.java))
        }
        ticketButton.setOnClickListener {
            startActivity(Intent(this, Ticket::class.java))
        }
        profileButton.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }

        databaseUst.close()
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
                    ustapay.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getUserUstaPay(UserIdModel.getId())))
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

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishAffinity()
            true
        } else {
            super.onKeyDown(keyCode, event)
        }
    }

}