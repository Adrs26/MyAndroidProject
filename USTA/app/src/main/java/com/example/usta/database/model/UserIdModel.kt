package com.example.usta.database.model

object UserIdModel {
    private var userId: Int = 0
    private var departureId: Int = 0
    private var totalTicket: Int = 0
    private val seatCode = ArrayList<String>()
    private var ticketCode: String = ""

    fun setId(userId: Int) {
        this.userId = userId
    }

    fun getId(): Int {
        return this.userId
    }

    fun setDepartureId(departureId: Int) {
        this.departureId = departureId
    }

    fun getDepartureId(): Int {
        return this.departureId
    }

    fun setTotalTicket(totalTicket: Int) {
        this.totalTicket = totalTicket
    }

    fun getTotalTicket(): Int {
        return this.totalTicket
    }

    fun setSeatCode(seatCode: ArrayList<String>) {
        this.seatCode.clear()
        for (seat in seatCode) {
            this.seatCode.add(seat)
        }
    }

    fun getSeatCode(): ArrayList<String> {
        return seatCode
    }

    fun setTicketCode(ticketCode: String) {
        this.ticketCode = ticketCode
    }

    fun getTicketCode(): String {
        return this.ticketCode
    }
}