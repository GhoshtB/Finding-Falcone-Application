package com.hfad.findingfalconeapplication.data

import com.hfad.findingfalconeapplication.data.model.CompleteData
import com.hfad.findingfalconeapplication.data.model.NewPlanets
import java.util.ArrayList

class Constants {

    companion object{
        const val baseurl ="https://findfalcone.herokuapp.com"
        var launchFlags=-1

        var vehiclList = arrayListOf<String>()
        var planetList = arrayListOf<String>()
        var planets: List<NewPlanets> = arrayListOf()
         var completeData: CompleteData= CompleteData("", arrayListOf<String>(), arrayListOf<String>())
    }
}