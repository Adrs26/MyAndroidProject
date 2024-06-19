package com.example.usta.database.model

class TicketListModel(
    val departureId: Int,
    val trainName: String,
    val departureDate: String,
    val departureTime: String,
    val estDepartureTime: String,
    val ticketPrice: Int,
    val trainClass: String
)