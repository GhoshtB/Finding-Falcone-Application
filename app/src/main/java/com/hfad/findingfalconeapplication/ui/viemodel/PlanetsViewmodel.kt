package com.hfad.findingfalconeapplication.ui.viemodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hfad.findingfalconeapplication.data.PlanetRepository
import com.hfad.findingfalconeapplication.data.PlanetUseCase
import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.Planets
import com.hfad.findingfalconeapplication.data.model.TokenData
import com.hfad.findingfalconeapplication.data.model.Vehicles
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlanetsViewmodel() : ViewModel() {

    val planetsData: MutableLiveData<List<Planets>> by lazy { MutableLiveData() }
    val vehiclesData: MutableLiveData<List<Vehicles>> by lazy { MutableLiveData() }
    val errorsData: MutableLiveData<String> by lazy { MutableLiveData() }
    val planetUseCase: PlanetUseCase = PlanetUseCase(PlanetRepository())
    suspend fun getPlanets() {
        planetUseCase.getPlanets({ data ->
            viewModelScope.launch(context = Dispatchers.Main) {
                planetsData.value = data
            }
        }, { errors ->/*errorsData.value=errors*/ })

    }

    suspend fun getVehicles() {

        planetUseCase.getVehicles({ data ->
            viewModelScope.launch(context = Dispatchers.Main) {
                vehiclesData.value = data
            }
        }, { errors ->/*errorsData.value=errors*/ })


    }

    suspend fun getToken(token: (TokenData) -> Unit) {
        planetUseCase.getToken({ data ->
            viewModelScope.launch(context = Dispatchers.Main) {
                token(data)
            }
        }, { errors -> errorsData.value = errors })

    }

    suspend fun findPlanet(data: CompleteData, token: (String) -> Unit, error: (String) -> Unit) {
        planetUseCase.findPlanet(data, { toke ->
            viewModelScope.launch { token(toke) }
        }, { err ->viewModelScope.launch { error(err)  } })
    }
}