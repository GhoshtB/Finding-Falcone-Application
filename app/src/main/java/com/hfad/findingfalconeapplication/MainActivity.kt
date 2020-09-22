package com.hfad.findingfalconeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.findingfalconeapplication.data.Constants
import com.hfad.findingfalconeapplication.data.PlanetRepository
import com.hfad.findingfalconeapplication.data.PlanetUseCase
import com.hfad.findingfalconeapplication.ui.fragment.MainFragment
import com.hfad.findingfalconeapplication.ui.fragment.PlanetsFragment
import com.hfad.findingfalconeapplication.ui.viemodel.PlanetsViewmodel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var planetUseCase: PlanetUseCase
    lateinit var planetsViewmodel: PlanetsViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        planetUseCase = PlanetUseCase(PlanetRepository())
        planetsViewmodel = ViewModelProvider(this)[PlanetsViewmodel::class.java]

        init()
    }

    private fun init() {
        launchFragment(MainFragment(), R.id.frameId)

        GlobalScope.launch {
            planetsViewmodel.getToken { data ->
                Constants.completeData.token = data.token
            }

            planetsViewmodel.getPlanets()
            planetsViewmodel.getVehicles()
        }

        planetsViewmodel.errorsData.observe(this, Observer { planets ->
            showToast(planets.toString())
        })


    }


    override fun onBackPressed() {

        var count = supportFragmentManager.backStackEntryCount
        if (count < 2) {
            super.onBackPressed()
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

}