package com.hfad.findingfalconeapplication.data

import com.hfad.findingfalconeapplication.api.ApiServices
import com.hfad.findingfalconeapplication.api.NetworkService
import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.Planets
import com.hfad.findingfalconeapplication.data.model.TokenData
import com.hfad.findingfalconeapplication.data.model.Vehicles
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import okhttp3.ResponseBody
import retrofit2.Response

class PlanetRepository:ApiRepositories {
    val service = NetworkService()
    fun getApiValues():ApiServices{
        return service.callApiServices(Constants.baseurl,ApiServices::class.java)
    }

    override suspend fun getPlanets(): Response<List<Planets>> {
        return GlobalScope.async {
            getApiValues().getPlanets().execute()
        }.await()
    }

    override suspend fun getVehicles(): Response<List<Vehicles>> {
        return GlobalScope.async {
            getApiValues().getVehicles().execute()
        }.await()
    }

    override suspend fun getToken(): Response<TokenData> {
        return GlobalScope.async {
            getApiValues().getToken().execute()
        }.await()
    }

    override suspend fun findPlanet(data: CompleteData): Response<ResponseBody> {
        return GlobalScope.async {
            getApiValues().findPlanet(data).execute()
        }.await()
    }
}