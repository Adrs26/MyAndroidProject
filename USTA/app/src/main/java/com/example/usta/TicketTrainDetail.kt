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

class TicketTrainDetail : AppCompatActivity() {
    private lateinit var backButton: ImageButton
    private lateinit var name: TextView
    private lateinit var train: TextView
    private lateinit var startStation: TextView
    private lateinit var finishStation: TextView
    private lateinit var price: TextView
    private lateinit var finishButton: Button
    private lateinit var cancelButton: Button

    private fun initComponents() {
        backButton = findViewById(R.id.backButtonETicket)
        name = findViewById(R.id.passengerName)
        train = findViewById(R.id.passengerTrain)
        startStation = findViewById(R.id.passengerStartStation)
        finishStation = findViewById(R.id.passengerFinishStation)
        price = findViewById(R.id.passengerTicketPrice)
        finishButton = findViewById(R.id.finishButtonETicket)
        cancelButton = findViewById(R.id.cancelButtonETicket)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_train_detail)

        initComponents()
        val databaseUst = DatabaseUst(this)

        name.text = databaseUst.getPassengerName(UserIdModel.getTicketCode())
        train.text = databaseUst.getPassengerTrain(UserIdModel.getTicketCode())
        startStation.text = databaseUst.getPassengerStartStation(UserIdModel.getTicketCode())
        finishStation.text = databaseUst.getPassengerFinishStation(UserIdModel.getTicketCode())
        price.text = resources.getString(R.string.formatted_money_value, NumberingFormat.thousandSeparator(databaseUst.getPassengerTicketPrice(UserIdModel.getTicketCode())))

        backButton.setOnClickListener {
            startActivity(Intent(this, Ticket::class.java))
        }

        finishButton.setOnClickListener {
            showPopUpFinishTicket()
        }

        cancelButton.setOnClickListener {
            showPopUpCancelTicket()
        }
    }

    private fun showPopUpFinishTicket() {
        val dialog = AlertDialog.Builder(this)
        val databaseUst = DatabaseUst(this)

        dialog.setTitle("Tiket akan diselesaikan")
        dialog.setMessage("Setelah diselesaikan, tiket tidak akan bisa digunakan lagi. Apakah anda yakin ingin menyelesaikan tiket?")

        dialog.setPositiveButton("Ya") { dialog1, _ ->
            databaseUst.updateTicketTrainStatus(UserIdModel.getTicketCode(), "FINISH")
            Toast.makeText(this, "Tiket berhasil diselesaikan", Toast.LENGTH_SHORT).show()

            dialog1.dismiss()
            startActivity(Intent(this, Ticket::class.java))
        }
        dialog.setNegativeButton("No") {dialog2, _ -> dialog2.dismiss()}

        val dialogCreate = dialog.create()
        dialogCreate.show()
    }

    private fun showPopUpCancelTicket() {
        val dialog = AlertDialog.Builder(this)

        dialog.setTitle("Tiket akan dibatalkan")
        dialog.setMessage("Setelah dibatalkan, saldo UstaPay akan dikembalikan. Apakah anda yakin ingin membatalkan tiket?")

        dialog.setPositiveButton("Ya") { dialog1, _ ->
            showReasonDialog()
            dialog1.dismiss()
        }
        dialog.setNegativeButton("Tidak") {dialog2, _ -> dialog2.dismiss()}

        val dialogCreate = dialog.create()
        dialogCreate.show()
    }

    private fun showReasonDialog() {
        val builder = android.app.AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.popup_cancel_reason, null)
        builder.setView(view)

        val databaseUst = DatabaseUst(this)

        val cancelButton: Button = view.findViewById(R.id.cancelButtonReason)
        val okButton: Button = view.findViewById(R.id.okButtonReason)

        val dialog = builder.create()

        okButton.setOnClickListener {
            databaseUst.updateTicketTrainStatus(UserIdModel.getTicketCode(), "CANCEL")
            databaseUst.updateStatusSeat(databaseUst.getDepartureId(UserIdModel.getTicketCode()), databaseUst.getSeatCode(UserIdModel.getTicketCode()), "ACTIVE")
            databaseUst.updateUserUstaPay(UserIdModel.getId(), databaseUst.getUserUstaPay(UserIdModel.getId()), databaseUst.getPassengerTicketPrice(UserIdModel.getTicketCode()))
            Toast.makeText(this, "Tiket berhasil dibatalkan, kami akan terus meningkatkan pelayanan kami", Toast.LENGTH_SHORT).show()

            dialog.dismiss()
            startActivity(Intent(this, Ticket::class.java))
        }
        cancelButton.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}