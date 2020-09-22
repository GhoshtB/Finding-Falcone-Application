package com.hfad.findingfalconeapplication.api

import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.Planets
import com.hfad.findingfalconeapplication.data.model.TokenData
import com.hfad.findingfalconeapplication.data.model.Vehicles
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {

    @GET("/planets")
    fun getPlanets(): Call<List<Planets>>

    @GET("/vehicles")
    fun getVehicles(): Call<List<Vehicles>>

    @Headers("Accept: application/json")
    @POST("/token")
    fun getToken(): Call<TokenData>

    @Headers("Accept: application/json", "Content-Type: application/json")
    @POST("/find")
    fun findPlanet(@Body data: CompleteData): Call<ResponseBody>
}