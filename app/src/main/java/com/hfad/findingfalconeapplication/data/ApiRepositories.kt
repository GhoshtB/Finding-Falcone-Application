package com.hfad.findingfalconeapplication.data

import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.Planets
import com.hfad.findingfalconeapplication.data.model.TokenData
import com.hfad.findingfalconeapplication.data.model.Vehicles
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body

interface ApiRepositories {

    suspend fun getPlanets(): Response<List<Planets>>

    suspend fun getVehicles(): Response<List<Vehicles>>

    suspend fun getToken(): Response<TokenData>

    suspend fun findPlanet(data: CompleteData): Response<ResponseBody>
}