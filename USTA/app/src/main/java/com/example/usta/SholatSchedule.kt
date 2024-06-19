package com.example.usta

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.usta.database.DatabaseUst
import com.example.usta.database.model.UserIdModel
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class SholatSchedule : AppCompatActivity() {
    private lateinit var username: TextView
    private lateinit var dateToday: TextView
    private lateinit var imsakTime: TextView
    private lateinit var shubuhTime: TextView
    private lateinit var dzuhurTime: TextView
    private lateinit var asharTime: TextView
    private lateinit var maghribTime: TextView
    private lateinit var isyaTime: TextView

    private fun initComponents() {
        username = findViewById(R.id.usernameSholatSchedule)
        dateToday = findViewById(R.id.dateToday)
        imsakTime = findViewById(R.id.imsakTime)
        shubuhTime = findViewById(R.id.shubuhTime)
        dzuhurTime = findViewById(R.id.dzuhurTime)
        asharTime = findViewById(R.id.asharTime)
        maghribTime = findViewById(R.id.maghribTime)
        isyaTime = findViewById(R.id.isyaTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sholat_schedule)

        initComponents()
        val databaseUst = DatabaseUst(this)

        username.text = databaseUst.getUsername(UserIdModel.getId())
        dateToday.text = getTodayDateFormat()
        imsakTime.text = resources.getString(R.string.imsak_time)
        shubuhTime.text = resources.getString(R.string.shubuh_time)
        dzuhurTime.text = resources.getString(R.string.dzuhur_time)
        asharTime.text = resources.getString(R.string.ashar_time)
        maghribTime.text = resources.getString(R.string.maghrib_time)
        isyaTime.text = resources.getString(R.string.isya_time)
    }

    private fun getTodayDateFormat(): String {
        val today: LocalDate = LocalDate.now()
        val day: Int = today.dayOfMonth
        val month: String = today.month.getDisplayName(TextStyle.FULL, Locale("id", "ID"))
        val year: Int = today.year

        return resources.getString(R.string.today_date, day, month, year)
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