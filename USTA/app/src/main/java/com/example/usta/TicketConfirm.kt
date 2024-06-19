package com.example.usta

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.NumberingFormat
import com.example.usta.util.PasswordHashing
import com.example.usta.util.RandomNumberGenerator
import com.google.android.material.textfield.TextInputEditText
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class TicketConfirm : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var trainName: TextView
    private lateinit var departureDate: TextView
    private lateinit var departureTime: TextView
    private lateinit var departureEstTime: TextView
    private lateinit var departurePrice: TextView
    private lateinit var trainClass: TextView
    private lateinit var name: TextView
    private lateinit var number: TextView
    private lateinit var seat: TextView
    private lateinit var editSeat: ImageButton
    private lateinit var ustaPay: TextView
    private lateinit var topUpButton: ImageButton
    private lateinit var priceTotal: TextView
    private lateinit var buyButton: Button

    private fun initComponents() {
        backButton = findViewById(R.id.backButtonTicketConfirm)
        trainName = findViewById(R.id.trainNameTicketConfirm)
        departureDate = findViewById(R.id.dateDepartureTicketConfirm)
        departureTime = findViewById(R.id.timeDepartureTicketConfirm)
        departureEstTime = findViewById(R.id.estTimeDepartureTicketConfirm)
        departurePrice = findViewById(R.id.ticketPriceTicketConfirm)
        trainClass = findViewById(R.id.trainClassTicketConfirm)
        name = findViewById(R.id.nameUserTicketConfirm)
        number = findViewById(R.id.numberUserTicketConfirm)
        seat = findViewById(R.id.seatCodeTicketConfirm)
        editSeat = findViewById(R.id.editSeatTicketConfirm)
        ustaPay = findViewById(R.id.ustaPayAmountTicketConfirm)
        topUpButton = findViewById(R.id.topUpButtonTicketConfirm)
        priceTotal = findViewById(R.id.priceTotalTicketConfirm)
        buyButton = findViewById(R.id.buyButtonTicketConfirm)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_confirm)

        initComponents()
        val databaseUst = DatabaseUst(this)

        trainName.text = databaseUst.getTrainName(UserIdModel.getDepartureId())
        departureDate.text = databaseUst.getDepartureDate(UserIdModel.getDepartureId())
        departureTime.text = databaseUst.getDepartureTime(UserIdModel.getDepartureId())
        departureEstTime.text = databaseUst.getDepartureEstTime(UserIdModel.getDepartureId())
        departurePrice.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getDeparturePrice(UserIdModel.getDepartureId())))
        trainClass.text = databaseUst.getTrainClass(UserIdModel.getDepartureId())

        name.text = databaseUst.getUserFullName(UserIdModel.getId())
        if (databaseUst.getUserNumber(UserIdModel.getId()) != "-") {
            number.text = databaseUst.getUserNumber(UserIdModel.getId())
        }

        name.setOnClickListener {
            showPopUpName()
        }
        number.setOnClickListener {
            showPopUpNumber()
        }

        seat.text = UserIdModel.getSeatCode().joinToString(separator = ", ")

        ustaPay.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getUserUstaPay(UserIdModel.getId())))
        priceTotal.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getDeparturePrice(UserIdModel.getDepartureId()) * UserIdModel.getTotalTicket()))

        backButton.setOnClickListener {
            startActivity(Intent(this, TicketList::class.java))
        }

        editSeat.setOnClickListener {
            finish()
        }

        topUpButton.setOnClickListener {
            showPopUpTopUp()
        }

        buyButton.setOnClickListener {
            val totalTicketPrice = databaseUst.getDeparturePrice(UserIdModel.getDepartureId()) * UserIdModel.getTotalTicket()

            when {
                number.text.toString() == "Nomor telepon" -> Toast.makeText(this, "Nomor telepon tidak boleh kosong", Toast.LENGTH_SHORT).show()
                databaseUst.getUserUstaPay(UserIdModel.getId()) < totalTicketPrice -> Toast.makeText(this, "Saldo UstaPay anda tidak cukup", Toast.LENGTH_SHORT).show()
                else -> {
                    databaseUst.updateUserUstaPay(UserIdModel.getId(), databaseUst.getUserUstaPay(UserIdModel.getId()), -totalTicketPrice)

                    for (seatCode in UserIdModel.getSeatCode()) {
                        databaseUst.insertTicketTrainData(RandomNumberGenerator.getRandomNumber().toString(), UserIdModel.getId(), name.text.toString(), number.text.toString(), UserIdModel.getDepartureId(), seatCode, getTodayDateFormat())
                        databaseUst.updateStatusSeat(UserIdModel.getDepartureId(), seatCode, "INACTIVE")
                    }

                    Toast.makeText(this, "Pembelian berhasil, tiket kereta bisa dicek pada menu tiket", Toast.LENGTH_SHORT).show()

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

    private fun showPopUpName() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_username_ticket_confirm, null)
        builder.setView(view)

        val name: TextInputEditText = view.findViewById<CardView>(R.id.namePopUpCardView).findViewById(R.id.namePopUpInputEditText)
        val cancelButton: Button = view.findViewById(R.id.cancelButtonPopUpName)
        val okButton: Button = view.findViewById(R.id.okButtonPopUpName)

        val dialog = builder.create()

        okButton.setOnClickListener {
            val stringName = name.text.toString()
            this.name.text = stringName

            dialog.dismiss()
        }
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun showPopUpNumber() {
        val builder = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_number_ticket_confirm, null)
        builder.setView(view)

        val number: TextInputEditText = view.findViewById<CardView>(R.id.numberCardViewPopUp).findViewById(R.id.numberInputEditTextPopUp)
        val cancelButton: Button = view.findViewById(R.id.cancelButtonPopUpNumber)
        val okButton: Button = view.findViewById(R.id.okButtonPopUpNumber)

        val dialog = builder.create()

        okButton.setOnClickListener {
            val stringNumber = number.text.toString()
            if (stringNumber.length < 11) {
                Toast.makeText(this, "Nomor telepon harus terdiri dari minimal 11 angka", Toast.LENGTH_SHORT).show()
            } else {
                this.number.text = stringNumber

                dialog.dismiss()
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