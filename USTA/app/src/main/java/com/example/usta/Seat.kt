package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import com.example.usta.util.SeatNumber

class Seat : AppCompatActivity() {
    private lateinit var seatA1: TextView
    private lateinit var seatA2: TextView
    private lateinit var seatA3: TextView
    private lateinit var seatA4: TextView
    private lateinit var seatB1: TextView
    private lateinit var seatB2: TextView
    private lateinit var seatB3: TextView
    private lateinit var seatB4: TextView
    private lateinit var seatC1: TextView
    private lateinit var seatC2: TextView
    private lateinit var seatC3: TextView
    private lateinit var seatC4: TextView
    private lateinit var seatD1: TextView
    private lateinit var seatD2: TextView
    private lateinit var seatD3: TextView
    private lateinit var seatD4: TextView
    private lateinit var seatE1: TextView
    private lateinit var seatE2: TextView
    private lateinit var seatE3: TextView
    private lateinit var seatE4: TextView
    private lateinit var seatF1: TextView
    private lateinit var seatF2: TextView
    private lateinit var seatF3: TextView
    private lateinit var seatF4: TextView
    private lateinit var undoButton: TextView
    private lateinit var nextButton: TextView
    private val databaseUst = DatabaseUst(this)
    private var fixTotalTicket: Int = UserIdModel.getTotalTicket()

    private fun initComponents() {
        seatA1 = findViewById(R.id.seatA1)
        seatA2 = findViewById(R.id.seatA2)
        seatA3 = findViewById(R.id.seatA3)
        seatA4 = findViewById(R.id.seatA4)
        seatB1 = findViewById(R.id.seatB1)
        seatB2 = findViewById(R.id.seatB2)
        seatB3 = findViewById(R.id.seatB3)
        seatB4 = findViewById(R.id.seatB4)
        seatC1 = findViewById(R.id.seatC1)
        seatC2 = findViewById(R.id.seatC2)
        seatC3 = findViewById(R.id.seatC3)
        seatC4 = findViewById(R.id.seatC4)
        seatD1 = findViewById(R.id.seatD1)
        seatD2 = findViewById(R.id.seatD2)
        seatD3 = findViewById(R.id.seatD3)
        seatD4 = findViewById(R.id.seatD4)
        seatE1 = findViewById(R.id.seatE1)
        seatE2 = findViewById(R.id.seatE2)
        seatE3 = findViewById(R.id.seatE3)
        seatE4 = findViewById(R.id.seatE4)
        seatF1 = findViewById(R.id.seatF1)
        seatF2 = findViewById(R.id.seatF2)
        seatF3 = findViewById(R.id.seatF3)
        seatF4 = findViewById(R.id.seatF4)
        undoButton = findViewById(R.id.undoSeatButton)
        nextButton = findViewById(R.id.confirmSeatButton)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seat)

        initComponents()
        val seatArray: ArrayList<String> = ArrayList()

        setAllSeatColor()

        seatA1.setOnClickListener { setSeatReady(seatA1, "A1", seatArray) }
        seatA2.setOnClickListener { setSeatReady(seatA2, "A2", seatArray) }
        seatA3.setOnClickListener { setSeatReady(seatA3, "A3", seatArray) }
        seatA4.setOnClickListener { setSeatReady(seatA4, "A4", seatArray) }
        seatB1.setOnClickListener { setSeatReady(seatB1, "B1", seatArray) }
        seatB2.setOnClickListener { setSeatReady(seatB2, "B2", seatArray) }
        seatB3.setOnClickListener { setSeatReady(seatB3, "B3", seatArray) }
        seatB4.setOnClickListener { setSeatReady(seatB4, "B4", seatArray) }
        seatC1.setOnClickListener { setSeatReady(seatC1, "C1", seatArray) }
        seatC2.setOnClickListener { setSeatReady(seatC2, "C2", seatArray) }
        seatC3.setOnClickListener { setSeatReady(seatC3, "C3", seatArray) }
        seatC4.setOnClickListener { setSeatReady(seatC4, "C4", seatArray) }
        seatD1.setOnClickListener { setSeatReady(seatD1, "D1", seatArray) }
        seatD2.setOnClickListener { setSeatReady(seatD2, "D2", seatArray) }
        seatD3.setOnClickListener { setSeatReady(seatD3, "D3", seatArray) }
        seatD4.setOnClickListener { setSeatReady(seatD4, "D4", seatArray) }
        seatE1.setOnClickListener { setSeatReady(seatE1, "E1", seatArray) }
        seatE2.setOnClickListener { setSeatReady(seatE2, "E2", seatArray) }
        seatE3.setOnClickListener { setSeatReady(seatE3, "E3", seatArray) }
        seatE4.setOnClickListener { setSeatReady(seatE4, "E4", seatArray) }
        seatF1.setOnClickListener { setSeatReady(seatF1, "F1", seatArray) }
        seatF2.setOnClickListener { setSeatReady(seatF2, "F2", seatArray) }
        seatF3.setOnClickListener { setSeatReady(seatF3, "F3", seatArray) }
        seatF4.setOnClickListener { setSeatReady(seatF4, "F4", seatArray) }

        undoButton.setOnClickListener {
            setAllSeatColor()
            fixTotalTicket = UserIdModel.getTotalTicket()
            seatArray.clear()
        }

        nextButton.setOnClickListener {
            UserIdModel.setSeatCode(seatArray)
            startActivity(Intent(this, TicketConfirm::class.java))
        }
    }

    private fun checkSeatStatus(seatCode: String): Boolean {
        return databaseUst.selectStatusSeat(UserIdModel.getDepartureId(), SeatNumber.getSeatNumber(seatCode)) == "ACTIVE"
    }

    private fun setSeatColor(seat: TextView, seatCode: String) {
        if (!checkSeatStatus(seatCode)) {
            seat.setBackgroundResource(R.drawable.button_red)
        } else if (checkSeatStatus(seatCode)) {
            seat.setBackgroundResource(R.drawable.button_darkblue)
        }
    }

    private fun setAllSeatColor() {
        setSeatColor(seatA1, "A1")
        setSeatColor(seatA2, "A2")
        setSeatColor(seatA3, "A3")
        setSeatColor(seatA4, "A4")
        setSeatColor(seatB1, "B1")
        setSeatColor(seatB2, "B2")
        setSeatColor(seatB3, "B3")
        setSeatColor(seatB4, "B4")
        setSeatColor(seatC1, "C1")
        setSeatColor(seatC2, "C2")
        setSeatColor(seatC3, "C3")
        setSeatColor(seatC4, "C4")
        setSeatColor(seatD1, "D1")
        setSeatColor(seatD2, "D2")
        setSeatColor(seatD3, "D3")
        setSeatColor(seatD4, "D4")
        setSeatColor(seatE1, "E1")
        setSeatColor(seatE2, "E2")
        setSeatColor(seatE3, "E3")
        setSeatColor(seatE4, "E4")
        setSeatColor(seatF1, "F1")
        setSeatColor(seatF2, "F2")
        setSeatColor(seatF3, "F3")
        setSeatColor(seatF4, "F4")
    }

    private fun setSeatReady(seat: TextView, seatCode: String, seatArray: ArrayList<String>) {
        if (checkSeatStatus(seatCode) && fixTotalTicket > 0) {
            seat.setBackgroundResource(R.drawable.button_shadow)
            seatArray.add(seatCode)
            fixTotalTicket--
        }
    }
}