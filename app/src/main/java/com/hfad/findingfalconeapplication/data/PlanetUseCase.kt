package com.hfad.findingfalconeapplication.data

import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.Planets
import com.hfad.findingfalconeapplication.data.model.TokenData
import com.hfad.findingfalconeapplication.data.model.Vehicles
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class PlanetUseCase(val apiRepositories: ApiRepositories) {

    suspend fun getPlanets( list:(List<Planets>)->Unit,error:(String)->Unit){
        GlobalScope.launch {
            val responses= apiRepositories.getPlanets()
            if (responses.isSuccessful){
                list.invoke(responses.body()!!)
            }else{
                error("Failed")
            }

        }
    }

    suspend fun getVehicles(list:(List<Vehicles>)->Unit,error:(String)->Unit){
        GlobalScope.launch {
            val responses= apiRepositories.getVehicles()
            if (responses.isSuccessful){
                list.invoke(responses.body()!!)
            }else{
                error("Failed")
            }

        }

    }

    suspend fun getToken(token:(TokenData)->Unit,error:(String)->Unit){
        GlobalScope.launch {
            val responses= apiRepositories.getToken()
            if (responses.isSuccessful){
                token(responses.body()!!)
            }else{
                error("Failed")
            }

        }

    }

    suspend fun findPlanet(data: CompleteData,token:(String)->Unit,error:(String)->Unit){
        val responses= apiRepositories.findPlanet(data)
        if (responses.isSuccessful){
            token.invoke(responses.body().toString())
        }else{
            error(responses.raw().message)
        }
    }

}