package com.example.usta.database.model

object StationModel {
    private var startStation: String = "Start"
    private var finishStation: String = "Finish"

    fun setStartStation(startStation: String) {
        this.startStation = startStation
    }

    fun getStartStation(): String {
        return this.startStation
    }

    fun setFinishStation(finishStation: String) {
        this.finishStation = finishStation
    }

    fun getFinishStation(): String {
        return this.finishStation
    }
}