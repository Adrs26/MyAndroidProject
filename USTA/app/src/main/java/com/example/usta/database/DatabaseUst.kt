package com.example.usta.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.usta.database.model.TicketFoodDrinkListModel
import com.example.usta.database.model.TicketListModel
import com.example.usta.database.model.TicketTrainHistoryListModel
import com.example.usta.database.model.TicketTrainListModel

class DatabaseUst(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "UstaDatabase"
        private const val DATABASE_VERSION = 16

        private const val TABLE_USER = "User"
        private const val USER_ID = "UserId"
        private const val USER_FULL_NAME = "UserFullName"
        private const val USER_NAME = "Username"
        private const val USER_PASSWORD = "UserPassword"
        private const val USER_USTAPAY = "UserUstaPay"
        private const val USER_NUMBER = "UserNumber"

        private const val TABLE_TRAIN = "Train"
        private const val TRAIN_ID = "TrainId"
        private const val TRAIN_NAME = "TrainName"

        private const val TABLE_STATION = "Station"
        private const val STATION_ID = "StationId"
        private const val STATION_CITY = "StationCity"

        private const val TABLE_CLASS = "Class"
        private const val CLASS_ID = "ClassId"
        private const val CLASS_NAME = "ClassName"

        private const val TABLE_DEPARTURE = "Departure"
        private const val DEPARTURE_ID = "DepartureId"
        private const val DEPARTURE_TRAIN = "DepartureTrain"
        private const val DEPARTURE_CLASS = "DepartureClass"
        private const val START_STATION = "StartStation"
        private const val FINISH_STATION = "FinishStation"
        private const val DATE_DEPARTURE = "DateDeparture"
        private const val TIME_DEPARTURE = "TimeDeparture"
        private const val EST_TIME_DEPARTURE = "EstTimeDeparture"
        private const val PRICE_TICKET = "PriceTicket"
        private const val SEAT_A1 = "SeatA1"
        private const val SEAT_A2 = "SeatA2"
        private const val SEAT_A3 = "SeatA3"
        private const val SEAT_A4 = "SeatA4"
        private const val SEAT_B1 = "SeatB1"
        private const val SEAT_B2 = "SeatB2"
        private const val SEAT_B3 = "SeatB3"
        private const val SEAT_B4 = "SeatB4"
        private const val SEAT_C1 = "SeatC1"
        private const val SEAT_C2 = "SeatC2"
        private const val SEAT_C3 = "SeatC3"
        private const val SEAT_C4 = "SeatC4"
        private const val SEAT_D1 = "SeatD1"
        private const val SEAT_D2 = "SeatD2"
        private const val SEAT_D3 = "SeatD3"
        private const val SEAT_D4 = "SeatD4"
        private const val SEAT_E1 = "SeatE1"
        private const val SEAT_E2 = "SeatE2"
        private const val SEAT_E3 = "SeatE3"
        private const val SEAT_E4 = "SeatE4"
        private const val SEAT_F1 = "SeatF1"
        private const val SEAT_F2 = "SeatF2"
        private const val SEAT_F3 = "SeatF3"
        private const val SEAT_F4 = "SeatF4"

        private const val TABLE_TICKET = "Ticket"
        private const val TICKET_CODE = "TicketCode"
        private const val TICKET_USER_ID = "TicketUserId"
        private const val PASSENGER_NAME = "PassengerName"
        private const val PASSENGER_NUMBER = "PassengerNumber"
        private const val TICKET_DEPARTURE = "TicketDeparture"
        private const val SEAT_CODE = "SeatCode"
        private const val PAY_STATUS = "PayStatus"
        private const val TICKET_STATUS = "TicketStatus"
        private const val TICKET_BUY_DATE = "TicketBuyDate"

        private const val TABLE_FOOD = "FoodDrink"
        private const val FOOD_CODE = "FoodCode"
        private const val FOOD_USER_ID = "FoodUserId"
        private const val FOOD_NAME = "FoodName"
        private const val FOOD_TOTAL = "FoodTotal"
        private const val FOOD_PRICE = "FoodPrice"
        private const val PAY_STATUS_FOOD = "PayStatusFood"
        private const val FOOD_STATUS = "FoodStatus"
        private const val FOOD_BUY_DATE = "FoodBuyDate"
    }

    override fun onConfigure(db: SQLiteDatabase) {
        super.onConfigure(db)
        db.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase) {
        var query = "CREATE TABLE IF NOT EXISTS " + TABLE_USER +
                "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_FULL_NAME + " TEXT NOT NULL," +
                USER_NAME + " TEXT NOT NULL UNIQUE," +
                USER_PASSWORD + " TEXT NOT NULL," +
                USER_USTAPAY + " INTEGER," +
                USER_NUMBER + " TEXT)"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_TRAIN +
                "(" +
                TRAIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TRAIN_NAME + " TEXT NOT NULL)"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_STATION +
                "(" +
                STATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                STATION_CITY + " TEXT NOT NULL)"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_CLASS +
                "(" +
                CLASS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                CLASS_NAME + " TEXT NOT NULL)"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_DEPARTURE +
                "(" +
                DEPARTURE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                DEPARTURE_TRAIN + " INTEGER NOT NULL," +
                DEPARTURE_CLASS + " INTEGER NOT NULL," +
                START_STATION + " INTEGER NOT NULL," +
                FINISH_STATION + " INTEGER NOT NULL," +
                DATE_DEPARTURE + " TEXT NOT NULL," +
                TIME_DEPARTURE + " TEXT NOT NULL," +
                EST_TIME_DEPARTURE + " TEXT NOT NULL," +
                PRICE_TICKET +  " INTEGER NOT NULL," +
                SEAT_A1  + " TEXT NOT NULL," +
                SEAT_A2  + " TEXT NOT NULL," +
                SEAT_A3  + " TEXT NOT NULL," +
                SEAT_A4  + " TEXT NOT NULL," +
                SEAT_B1  + " TEXT NOT NULL," +
                SEAT_B2  + " TEXT NOT NULL," +
                SEAT_B3  + " TEXT NOT NULL," +
                SEAT_B4  + " TEXT NOT NULL," +
                SEAT_C1  + " TEXT NOT NULL," +
                SEAT_C2  + " TEXT NOT NULL," +
                SEAT_C3  + " TEXT NOT NULL," +
                SEAT_C4  + " TEXT NOT NULL," +
                SEAT_D1  + " TEXT NOT NULL," +
                SEAT_D2  + " TEXT NOT NULL," +
                SEAT_D3  + " TEXT NOT NULL," +
                SEAT_D4  + " TEXT NOT NULL," +
                SEAT_E1  + " TEXT NOT NULL," +
                SEAT_E2  + " TEXT NOT NULL," +
                SEAT_E3  + " TEXT NOT NULL," +
                SEAT_E4  + " TEXT NOT NULL," +
                SEAT_F1  + " TEXT NOT NULL," +
                SEAT_F2  + " TEXT NOT NULL," +
                SEAT_F3  + " TEXT NOT NULL," +
                SEAT_F4  + " TEXT NOT NULL," +
                "FOREIGN KEY ($DEPARTURE_TRAIN) REFERENCES $TABLE_TRAIN($TRAIN_ID)," +
                "FOREIGN KEY ($DEPARTURE_CLASS) REFERENCES $TABLE_CLASS($CLASS_ID)," +
                "FOREIGN KEY ($START_STATION) REFERENCES $TABLE_STATION($STATION_ID)," +
                "FOREIGN KEY ($FINISH_STATION) REFERENCES $TABLE_STATION($STATION_ID))"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_TICKET +
                "(" +
                TICKET_CODE + " TEXT PRIMARY KEY," +
                TICKET_USER_ID + " INTEGER NOT NULL," +
                PASSENGER_NAME + " TEXT NOT NULL," +
                PASSENGER_NUMBER + " TEXT NOT NULL," +
                TICKET_DEPARTURE + " INTEGER NOT NULL," +
                SEAT_CODE + " TEXT NOT NULL," +
                PAY_STATUS + " TEXT NOT NULL," +
                TICKET_STATUS + " TEXT NOT NULL," +
                TICKET_BUY_DATE + " TEXT NOT NULL," +
                "FOREIGN KEY ($TICKET_USER_ID) REFERENCES $TABLE_USER($USER_ID)," +
                "FOREIGN KEY ($TICKET_DEPARTURE) REFERENCES $TABLE_DEPARTURE($DEPARTURE_ID))"
        db.execSQL(query)

        query = "CREATE TABLE IF NOT EXISTS " + TABLE_FOOD +
                "(" +
                FOOD_CODE + " TEXT PRIMARY KEY," +
                FOOD_USER_ID + " INTEGER NOT NULL," +
                FOOD_NAME + " TEXT NOT NULL," +
                FOOD_TOTAL + " INTEGER NOT NULL," +
                FOOD_PRICE + " INTEGER NOT NULL," +
                PAY_STATUS_FOOD + " TEXT NOT NULL," +
                FOOD_STATUS + " TEXT NOT NULL," +
                FOOD_BUY_DATE + " TEXT NOT NULL," +
                "FOREIGN KEY ($FOOD_USER_ID) REFERENCES $TABLE_USER($USER_ID))"
        db.execSQL(query)

        insertInitialDataTrain(db)
        insertInitialDataStation(db)
        insertInitialDataClass(db)
        insertInitialDataDeparture(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS $TABLE_TICKET")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_DEPARTURE")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_FOOD")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_TRAIN")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_STATION")
            db.execSQL("DROP TABLE IF EXISTS $TABLE_CLASS")
            onCreate(db)
        }
    }

    private fun insertInitialDataTrain(db: SQLiteDatabase) {
        val trains = listOf(
            ContentValues().apply {
                put(TRAIN_NAME, "Argo Bromo")
            },
            ContentValues().apply {
                put(TRAIN_NAME, "Argo Jati")
            },
            ContentValues().apply {
                put(TRAIN_NAME, "Argo Muria")
            })

        for (train in trains) {
            db.insert(TABLE_TRAIN, null, train)
        }
    }

    private fun insertInitialDataStation(db: SQLiteDatabase) {
        val stations = listOf(
            ContentValues().apply {
                put(STATION_CITY, "JAKARTA")
            },
            ContentValues().apply {
                put(STATION_CITY, "BANDUNG")
            },
            ContentValues().apply {
                put(STATION_CITY, "SEMARANG")
            })

        for (station in stations) {
            db.insert(TABLE_STATION, null, station)
        }
    }

    private fun insertInitialDataClass(db: SQLiteDatabase) {
        val classes = listOf(
            ContentValues().apply {
                put(CLASS_NAME, "Eksekutif")
            },
            ContentValues().apply {
                put(CLASS_NAME, "Bisnis")
            },
            ContentValues().apply {
                put(CLASS_NAME, "Ekonomi")
            })

        for (clas in classes) {
            db.insert(TABLE_CLASS, null, clas)
        }
    }

    private fun insertInitialDataDeparture(db: SQLiteDatabase) {
        val departures = listOf(
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 1)
                put(DEPARTURE_CLASS, 1)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "08.00 - 10.00")
                put(EST_TIME_DEPARTURE, "2 jam 0 menit")
                put(PRICE_TICKET, 480000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 1)
                put(DEPARTURE_CLASS, 2)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "08.00 - 10.00")
                put(EST_TIME_DEPARTURE, "2 jam 0 menit")
                put(PRICE_TICKET, 400000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 1)
                put(DEPARTURE_CLASS, 3)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "08.00 - 10.00")
                put(EST_TIME_DEPARTURE, "2 jam 0 menit")
                put(PRICE_TICKET, 320000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 2)
                put(DEPARTURE_CLASS, 1)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "10.30 - 12.40")
                put(EST_TIME_DEPARTURE, "2 jam 10 menit")
                put(PRICE_TICKET, 450000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 2)
                put(DEPARTURE_CLASS, 3)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "10.30 - 12.40")
                put(EST_TIME_DEPARTURE, "2 jam 10 menit")
                put(PRICE_TICKET, 325000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 1)
                put(DEPARTURE_CLASS, 1)
                put(START_STATION, 1)
                put(FINISH_STATION, 3)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "07.00 - 13.30")
                put(EST_TIME_DEPARTURE, "6 jam 30 menit")
                put(PRICE_TICKET, 600000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 1)
                put(DEPARTURE_CLASS, 3)
                put(START_STATION, 1)
                put(FINISH_STATION, 3)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "07.00 - 13.30")
                put(EST_TIME_DEPARTURE, "6 jam 30 menit")
                put(PRICE_TICKET, 360000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 2)
                put(DEPARTURE_CLASS, 1)
                put(START_STATION, 1)
                put(FINISH_STATION, 3)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "09.00 - 15.30")
                put(EST_TIME_DEPARTURE, "6 jam 30 menit")
                put(PRICE_TICKET, 550000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 3)
                put(DEPARTURE_CLASS, 3)
                put(START_STATION, 1)
                put(FINISH_STATION, 3)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "08.45 - 15.30")
                put(EST_TIME_DEPARTURE, "6 jam 45 menit")
                put(PRICE_TICKET, 310000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            },
            ContentValues().apply {
                put(DEPARTURE_TRAIN, 3)
                put(DEPARTURE_CLASS, 1)
                put(START_STATION, 1)
                put(FINISH_STATION, 2)
                put(DATE_DEPARTURE, "Sabtu, 1 Juni 2024")
                put(TIME_DEPARTURE, "11.00 - 13.15")
                put(EST_TIME_DEPARTURE, "2 jam 15 menit")
                put(PRICE_TICKET, 450000)
                put(SEAT_A1, "ACTIVE")
                put(SEAT_A2, "ACTIVE")
                put(SEAT_A3, "ACTIVE")
                put(SEAT_A4, "ACTIVE")
                put(SEAT_B1, "ACTIVE")
                put(SEAT_B2, "ACTIVE")
                put(SEAT_B3, "ACTIVE")
                put(SEAT_B4, "ACTIVE")
                put(SEAT_C1, "ACTIVE")
                put(SEAT_C2, "ACTIVE")
                put(SEAT_C3, "ACTIVE")
                put(SEAT_C4, "ACTIVE")
                put(SEAT_D1, "ACTIVE")
                put(SEAT_D2, "ACTIVE")
                put(SEAT_D3, "ACTIVE")
                put(SEAT_D4, "ACTIVE")
                put(SEAT_E1, "ACTIVE")
                put(SEAT_E2, "ACTIVE")
                put(SEAT_E3, "ACTIVE")
                put(SEAT_E4, "ACTIVE")
                put(SEAT_F1, "ACTIVE")
                put(SEAT_F2, "ACTIVE")
                put(SEAT_F3, "ACTIVE")
                put(SEAT_F4, "ACTIVE")
            })

        for (departure in departures) {
            db.insert(TABLE_DEPARTURE, null, departure)
        }
    }

    fun signUpAccount(userFullName: String, username: String, userPassword: String, userUstaPay: Int, userNumber: String): Boolean {
        val db = this.readableDatabase
        val cv = ContentValues()
        var result = false

        cv.put(USER_FULL_NAME, userFullName)
        cv.put(USER_NAME, username)
        cv.put(USER_PASSWORD, userPassword)
        cv.put(USER_USTAPAY, userUstaPay)
        cv.put(USER_NUMBER, userNumber)

        val resultLong: Long = db.insert(TABLE_USER, null, cv)
        if (resultLong != -1L) {
            result = true
            Toast.makeText(context, "Akun pengguna berhasil dibuat", Toast.LENGTH_SHORT).show()
        }

        return result
    }

    fun loginAccount(username: String, userPassword: String): Boolean {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_NAME = ? AND $USER_PASSWORD = ?"
        val dataUser = arrayOf(username, userPassword)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, dataUser)
        var result = false

        if (cursor.moveToNext()) {
            result = true
        } else {
            Toast.makeText(context, "Email atau password pengguna salah", Toast.LENGTH_SHORT).show()
        }

        cursor.close()
        return result
    }

    fun getUserId(username: String): Int {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_NAME = ?"
        val dataUser = arrayOf(username)
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, dataUser)
        var userId = 0

        if (cursor.moveToNext()) {
            userId = cursor.getInt(0)
        }

        cursor.close()
        return userId
    }

    fun getUserFullName(userId: Int): String {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_ID = $userId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var fullName = "User"

        if (cursor.moveToNext()) {
            fullName = cursor.getString(1)
        }

        cursor.close()
        return fullName
    }

    fun getUsername(userId: Int): String {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_ID = $userId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var username = "User"

        if (cursor.moveToNext()) {
            username = cursor.getString(2)
        }

        cursor.close()
        return username
    }

    fun getUserPassword(userId: Int): String {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_ID = $userId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var password = ""

        if (cursor.moveToNext()) {
            password = cursor.getString(3)
        }

        cursor.close()
        return password
    }

    fun getUserUstaPay(userId: Int): Int {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_ID = $userId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var amount = 0

        if (cursor.moveToNext()) {
            amount = cursor.getInt(4)
        }

        cursor.close()
        return amount
    }

    fun getUserNumber(userId: Int): String {
        val query = "SELECT * FROM $TABLE_USER WHERE $USER_ID = $userId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var userNumber = ""

        if (cursor.moveToNext()) {
            userNumber = cursor.getString(5)
        }

        cursor.close()
        return userNumber
    }

    fun updateUserFullName(userId: Int, newName: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(USER_FULL_NAME, newName)
        val whereClause = "$USER_ID = ?"
        val whereArgs = arrayOf(userId.toString())

        db.update(TABLE_USER, cv, whereClause, whereArgs)
    }

    fun updateUserNumber(userId: Int, newNumber: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put(USER_NUMBER, newNumber)
        val whereClause = "$USER_ID = ?"
        val whereArgs = arrayOf(userId.toString())

        db.update(TABLE_USER, cv, whereClause, whereArgs)
    }

    fun updateUserUstaPay(userId: Int, oldAmount: Int, newAmount: Int) {
        val db = this.writableDatabase
        val cv = ContentValues()
        val amount = oldAmount + newAmount

        cv.put(USER_USTAPAY, amount)
        val whereClause = "$USER_ID = ?"
        val whereArgs = arrayOf(userId.toString())

        db.update(TABLE_USER, cv, whereClause, whereArgs)
    }

    fun selectTicketList(startStation: String, finishStation: String): List<TicketListModel> {
        val listTicket = ArrayList<TicketListModel>()
        val db = this.readableDatabase
        val query = "SELECT $TABLE_DEPARTURE.$DEPARTURE_ID, $TABLE_TRAIN.$TRAIN_NAME, T1.$STATION_CITY, T2.$STATION_CITY, $TABLE_DEPARTURE.$DATE_DEPARTURE, $TABLE_DEPARTURE.$TIME_DEPARTURE, $TABLE_DEPARTURE.$EST_TIME_DEPARTURE, $TABLE_DEPARTURE.$PRICE_TICKET, $TABLE_CLASS.$CLASS_NAME " +
                "FROM $TABLE_DEPARTURE " +
                "JOIN $TABLE_TRAIN ON ($TABLE_DEPARTURE.$DEPARTURE_TRAIN = $TABLE_TRAIN.$TRAIN_ID) " +
                "JOIN $TABLE_STATION AS T1 ON ($TABLE_DEPARTURE.$START_STATION = T1.$STATION_ID) " +
                "JOIN $TABLE_STATION AS T2 ON ($TABLE_DEPARTURE.$FINISH_STATION = T2.$STATION_ID) " +
                "JOIN $TABLE_CLASS ON ($TABLE_DEPARTURE.$DEPARTURE_CLASS = $TABLE_CLASS.$CLASS_ID) " +
                "WHERE T1.$STATION_CITY = ? AND T2.$STATION_CITY = ?"
        val dataUser = arrayOf(startStation, finishStation)
        val cursor = db.rawQuery(query, dataUser)

        while (cursor.moveToNext()) {
            val departureId: Int = cursor.getInt(0)
            val trainName: String = cursor.getString(1)
            val dateDeparture: String = cursor.getString(4)
            val timeDeparture: String = cursor.getString(5)
            val estTimeDeparture: String = cursor.getString(6)
            val ticketPrice: Int = cursor.getInt(7)
            val trainClass: String = cursor.getString(8)

            listTicket.add(TicketListModel(departureId, trainName, dateDeparture, timeDeparture, estTimeDeparture, ticketPrice, trainClass))
        }

        cursor.close()
        return listTicket
    }

    fun selectStatusSeat(departureId: Int, seatNumber: Int): String {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_DEPARTURE WHERE $DEPARTURE_ID = ?"
        val dataUser = arrayOf(departureId.toString())
        val cursor = db.rawQuery(query, dataUser)
        var seatStatus = "INACTIVE"

        if (cursor.moveToNext()) {
            seatStatus = cursor.getString(seatNumber)
        }

        cursor.close()
        return seatStatus
    }

    fun getTrainName(departureId: Int): String {
        val query = "SELECT $TABLE_TRAIN.$TRAIN_NAME FROM $TABLE_DEPARTURE " +
                "JOIN $TABLE_TRAIN ON ($TABLE_DEPARTURE.$DEPARTURE_TRAIN = $TABLE_TRAIN.$TRAIN_ID) " +
                "WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var trainName = ""

        if (cursor.moveToNext()) {
            trainName = cursor.getString(0)
        }

        cursor.close()
        return trainName
    }

    fun getDepartureDate(departureId: Int): String {
        val query = "SELECT $DATE_DEPARTURE FROM $TABLE_DEPARTURE WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var departureDate = ""

        if (cursor.moveToNext()) {
            departureDate = cursor.getString(0)
        }

        cursor.close()
        return departureDate
    }

    fun getDepartureTime(departureId: Int): String {
        val query = "SELECT $TIME_DEPARTURE FROM $TABLE_DEPARTURE WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var departureTime = ""

        if (cursor.moveToNext()) {
            departureTime = cursor.getString(0)
        }

        cursor.close()
        return departureTime
    }

    fun getDepartureEstTime(departureId: Int): String {
        val query = "SELECT $EST_TIME_DEPARTURE FROM $TABLE_DEPARTURE WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var departureEstTime = ""

        if (cursor.moveToNext()) {
            departureEstTime = cursor.getString(0)
        }

        cursor.close()
        return departureEstTime
    }

    fun getDeparturePrice(departureId: Int): Int {
        val query = "SELECT $PRICE_TICKET FROM $TABLE_DEPARTURE WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var departurePrice = 0

        if (cursor.moveToNext()) {
            departurePrice = cursor.getInt(0)
        }

        cursor.close()
        return departurePrice
    }

    fun getTrainClass(departureId: Int): String {
        val query = "SELECT $TABLE_CLASS.$CLASS_NAME FROM $TABLE_DEPARTURE " +
                "JOIN $TABLE_CLASS ON ($TABLE_DEPARTURE.$DEPARTURE_CLASS = $TABLE_CLASS.$CLASS_ID) " +
                "WHERE $DEPARTURE_ID = $departureId"
        val db = this.readableDatabase
        val cursor = db.rawQuery(query, null)
        var className = ""

        if (cursor.moveToNext()) {
            className = cursor.getString(0)
        }

        cursor.close()
        return className
    }

    fun insertTicketTrainData(ticketCode: String, userId: Int, name: String, number: String, departureId: Int, seatCode: String, ticketBuyDate: String) {
        val db = this.readableDatabase
        val cv = ContentValues()

        cv.put(TICKET_CODE, ticketCode)
        cv.put(TICKET_USER_ID, userId)
        cv.put(PASSENGER_NAME, name)
        cv.put(PASSENGER_NUMBER, number)
        cv.put(TICKET_DEPARTURE, departureId)
        cv.put(SEAT_CODE, seatCode)
        cv.put(PAY_STATUS, "Lunas")
        cv.put(TICKET_STATUS, "WAITING")
        cv.put(TICKET_BUY_DATE, ticketBuyDate)

        db.insert(TABLE_TICKET, null, cv)
    }

    fun updateStatusSeat(departureId: Int, seatCode: String, status: String) {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("Seat$seatCode", status)
        val whereClause = "$DEPARTURE_ID = ?"
        val whereArgs = arrayOf(departureId.toString())

        db.update(TABLE_DEPARTURE, cv, whereClause, whereArgs)
    }

    fun selectTicketTrainData(userId: Int, ticketStatus: String): List<TicketTrainListModel> {
        val listTicket = ArrayList<TicketTrainListModel>()
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_USER.$USER_ID, $TABLE_TRAIN.$TRAIN_NAME, $TABLE_DEPARTURE.$DATE_DEPARTURE, $TABLE_DEPARTURE.$TIME_DEPARTURE, $TABLE_DEPARTURE.$EST_TIME_DEPARTURE, $TABLE_CLASS.$CLASS_NAME, $TABLE_TICKET.$SEAT_CODE, $TABLE_TICKET.$TICKET_STATUS " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_DEPARTURE.$DEPARTURE_ID = $TABLE_TICKET.$TICKET_DEPARTURE) " +
                "JOIN $TABLE_USER ON ($TABLE_USER.$USER_ID = $TABLE_TICKET.$TICKET_USER_ID) " +
                "JOIN $TABLE_TRAIN ON ($TABLE_DEPARTURE.$DEPARTURE_TRAIN = $TABLE_TRAIN.$TRAIN_ID) " +
                "JOIN $TABLE_CLASS ON ($TABLE_DEPARTURE.$DEPARTURE_CLASS = $TABLE_CLASS.$CLASS_ID) " +
                "WHERE $TABLE_USER.$USER_ID = ? AND $TABLE_TICKET.$TICKET_STATUS = ?"
        val dataUser = arrayOf(userId.toString(), ticketStatus)
        val cursor = db.rawQuery(query, dataUser)

        while (cursor.moveToNext()) {
            val ticketCode = cursor.getString(0)
            val trainName = cursor.getString(2)
            val departureDate = cursor.getString(3)
            val departureTime = cursor.getString(4)
            val departureEstTime = cursor.getString(5)
            val trainClass = cursor.getString(6)
            val seatCode = cursor.getString(7)

            listTicket.add(TicketTrainListModel(ticketCode, trainName, departureDate, departureTime, departureEstTime, trainClass, seatCode))
        }

        cursor.close()
        return listTicket
    }

    fun selectTicketTrainDataHistory(userId: Int, ticketStatus1: String, ticketStatus2: String): List<TicketTrainHistoryListModel> {
        val listTicket = ArrayList<TicketTrainHistoryListModel>()
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_USER.$USER_ID, $TABLE_TRAIN.$TRAIN_NAME, $TABLE_DEPARTURE.$DATE_DEPARTURE, $TABLE_DEPARTURE.$TIME_DEPARTURE, $TABLE_DEPARTURE.$EST_TIME_DEPARTURE, $TABLE_CLASS.$CLASS_NAME, $TABLE_TICKET.$SEAT_CODE, $TABLE_TICKET.$TICKET_STATUS " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_DEPARTURE.$DEPARTURE_ID = $TABLE_TICKET.$TICKET_DEPARTURE) " +
                "JOIN $TABLE_USER ON ($TABLE_USER.$USER_ID = $TABLE_TICKET.$TICKET_USER_ID) " +
                "JOIN $TABLE_TRAIN ON ($TABLE_DEPARTURE.$DEPARTURE_TRAIN = $TABLE_TRAIN.$TRAIN_ID) " +
                "JOIN $TABLE_CLASS ON ($TABLE_DEPARTURE.$DEPARTURE_CLASS = $TABLE_CLASS.$CLASS_ID) " +
                "WHERE $TABLE_USER.$USER_ID = ? AND ($TABLE_TICKET.$TICKET_STATUS = ? OR $TABLE_TICKET.$TICKET_STATUS = ?)"
        val dataUser = arrayOf(userId.toString(), ticketStatus1, ticketStatus2)
        val cursor = db.rawQuery(query, dataUser)

        while (cursor.moveToNext()) {
            val ticketCode = cursor.getString(0)
            val trainName = cursor.getString(2)
            val departureDate = cursor.getString(3)
            val departureTime = cursor.getString(4)
            val departureEstTime = cursor.getString(5)
            val trainClass = cursor.getString(6)
            val seatCode = cursor.getString(7)
            val ticketStatus20 = cursor.getString(8)

            listTicket.add(TicketTrainHistoryListModel(ticketCode, trainName, departureDate, departureTime, departureEstTime, trainClass, seatCode, ticketStatus20))
        }

        cursor.close()
        return listTicket
    }

    fun insertFoodDrinkData(foodCode: String, foodUserId: Int, foodName: String, foodTotal: Int, foodPrice: Int, foodBuyDate: String) {
        val db = this.readableDatabase
        val cv = ContentValues()

        cv.put(FOOD_CODE, foodCode)
        cv.put(FOOD_USER_ID, foodUserId)
        cv.put(FOOD_NAME, foodName)
        cv.put(FOOD_TOTAL, foodTotal)
        cv.put(FOOD_PRICE, foodPrice)
        cv.put(PAY_STATUS_FOOD, "Lunas")
        cv.put(FOOD_STATUS, "WAITING")
        cv.put(FOOD_BUY_DATE, foodBuyDate)

        db.insert(TABLE_FOOD, null, cv)
    }

    fun selectTicketFoodDrinkData(userId: Int, ticketStatus: String): List<TicketFoodDrinkListModel> {
        val listTicket = ArrayList<TicketFoodDrinkListModel>()
        val db = this.readableDatabase
        val query =
            "SELECT $TABLE_USER.$USER_ID, $TABLE_FOOD.$FOOD_CODE, $TABLE_FOOD.$FOOD_NAME, $TABLE_FOOD.$FOOD_TOTAL, $TABLE_FOOD.$FOOD_PRICE, $TABLE_FOOD.$FOOD_STATUS " +
                    "FROM $TABLE_FOOD " +
                    "JOIN $TABLE_USER ON ($TABLE_USER.$USER_ID = $TABLE_FOOD.$FOOD_USER_ID) " +
                    "WHERE $TABLE_USER.$USER_ID = ? AND $TABLE_FOOD.$FOOD_STATUS = ?"
        val dataUser = arrayOf(userId.toString(), ticketStatus)
        val cursor = db.rawQuery(query, dataUser)

        while (cursor.moveToNext()) {
            val ticketCode = cursor.getString(1)
            val foodName = cursor.getString(2)
            val foodTotal = cursor.getInt(3)
            val foodPrice = cursor.getInt(4)

            listTicket.add(TicketFoodDrinkListModel(ticketCode, foodName, foodTotal, foodPrice))
        }

        cursor.close()
        return listTicket
    }

    fun updateTicketTrainStatus(ticketCode: String, ticketStatus: String) {
        val db = this.readableDatabase
        val cv = ContentValues()

        cv.put(TICKET_STATUS, ticketStatus)
        val whereClause = "$TICKET_CODE = ?"
        val whereArgs = arrayOf(ticketCode)

        db.update(TABLE_TICKET, cv, whereClause, whereArgs)
    }

    fun updateTicketFoodDrinkStatus(ticketCode: String, ticketStatus: String) {
        val db = this.readableDatabase
        val cv = ContentValues()

        cv.put(FOOD_STATUS, ticketStatus)
        val whereClause = "$FOOD_CODE = ?"
        val whereArgs = arrayOf(ticketCode)

        db.update(TABLE_FOOD, cv, whereClause, whereArgs)
    }

    fun getDepartureId(ticketCode: String): Int {
        val db = this.readableDatabase
        val query = "SELECT $TICKET_DEPARTURE FROM $TABLE_TICKET WHERE $TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var id = 0

        if (cursor.moveToNext()) {
            id = cursor.getInt(0)
        }

        cursor.close()
        return id
    }

    fun getSeatCode(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $SEAT_CODE FROM $TABLE_TICKET WHERE $TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var seatCode = ""

        if (cursor.moveToNext()) {
            seatCode = cursor.getString(0)
        }

        cursor.close()
        return seatCode
    }

    fun getPassengerName(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $PASSENGER_NAME FROM $TABLE_TICKET WHERE $TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var name = ""

        if (cursor.moveToNext()) {
            name = cursor.getString(0)
        }

        cursor.close()
        return name
    }

    fun getPassengerTrain(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_TRAIN.$TRAIN_NAME, $TABLE_TICKET.$SEAT_CODE " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_TICKET.$TICKET_DEPARTURE = $TABLE_DEPARTURE.$DEPARTURE_ID) " +
                "JOIN $TABLE_TRAIN ON ($TABLE_DEPARTURE.$DEPARTURE_TRAIN = $TABLE_TRAIN.$TRAIN_ID) " +
                "WHERE $TABLE_TICKET.$TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var result = ""

        if (cursor.moveToNext()) {
            val train = cursor.getString(1)
            val seat = cursor.getString(2)
            result = "$train/$seat"
        }

        cursor.close()
        return result
    }

    fun getPassengerStartStation(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_STATION.$STATION_CITY " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_TICKET.$TICKET_DEPARTURE = $TABLE_DEPARTURE.$DEPARTURE_ID) " +
                "JOIN $TABLE_STATION ON ($TABLE_DEPARTURE.$START_STATION = $TABLE_STATION.$STATION_ID) " +
                "WHERE $TABLE_TICKET.$TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var station = ""

        if (cursor.moveToNext()) {
            station = cursor.getString(1)
        }

        cursor.close()
        return station
    }

    fun getPassengerFinishStation(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_STATION.$STATION_CITY " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_TICKET.$TICKET_DEPARTURE = $TABLE_DEPARTURE.$DEPARTURE_ID) " +
                "JOIN $TABLE_STATION ON ($TABLE_DEPARTURE.$FINISH_STATION = $TABLE_STATION.$STATION_ID) " +
                "WHERE $TABLE_TICKET.$TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var station = ""

        if (cursor.moveToNext()) {
            station = cursor.getString(1)
        }

        cursor.close()
        return station
    }

    fun getPassengerTicketPrice(ticketCode: String): Int {
        val db = this.readableDatabase
        val query = "SELECT $TABLE_TICKET.$TICKET_CODE, $TABLE_DEPARTURE.$PRICE_TICKET " +
                "FROM $TABLE_TICKET " +
                "JOIN $TABLE_DEPARTURE ON ($TABLE_TICKET.$TICKET_DEPARTURE = $TABLE_DEPARTURE.$DEPARTURE_ID) " +
                "WHERE $TABLE_TICKET.$TICKET_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var price = 0

        if (cursor.moveToNext()) {
            price = cursor.getInt(1)
        }

        cursor.close()
        return price
    }

    fun getFoodName(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $FOOD_NAME FROM $TABLE_FOOD WHERE $FOOD_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var name = ""

        if (cursor.moveToNext()) {
            name = cursor.getString(0)
        }

        cursor.close()
        return name
    }

    fun getFoodBuyDate(ticketCode: String): String {
        val db = this.readableDatabase
        val query = "SELECT $FOOD_BUY_DATE FROM $TABLE_FOOD WHERE $FOOD_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var date = ""

        if (cursor.moveToNext()) {
            date = cursor.getString(0)
        }

        cursor.close()
        return date
    }

    fun getFoodTotal(ticketCode: String): Int {
        val db = this.readableDatabase
        val query = "SELECT $FOOD_TOTAL FROM $TABLE_FOOD WHERE $FOOD_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var total = 0

        if (cursor.moveToNext()) {
            total = cursor.getInt(0)
        }

        cursor.close()
        return total
    }

    fun getFoodPrice(ticketCode: String): Int {
        val db = this.readableDatabase
        val query = "SELECT $FOOD_PRICE FROM $TABLE_FOOD WHERE $FOOD_CODE = ?"
        val dataUser = arrayOf(ticketCode)
        val cursor = db.rawQuery(query, dataUser)
        var price = 0

        if (cursor.moveToNext()) {
            price = cursor.getInt(0)
        }

        cursor.close()
        return price
    }
}