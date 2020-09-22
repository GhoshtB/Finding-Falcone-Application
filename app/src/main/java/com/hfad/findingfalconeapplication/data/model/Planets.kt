package com.hfad.findingfalconeapplication.data.model


data class Planets(
    val name: String,
    val distance: String
)

data class NewPlanets(
    val name: String,
    val distance: String,
    val image: Int,
    var visibility: Boolean
)

data class Vehicles(
    val name: String,
    val total_no: Int,
    val max_distance: Int,
    val speed: Int
)

data class NewVehicles(
    val name: String,
    val total_no: Int,
    val max_distance: Int,
    val speed: Int,
    var visibility: Boolean
)

data class TokenData(val token: String)

data class CompleteData(
    var token: String,
    var vehicles: ArrayList<String>,
    var planets: ArrayList<String>
)