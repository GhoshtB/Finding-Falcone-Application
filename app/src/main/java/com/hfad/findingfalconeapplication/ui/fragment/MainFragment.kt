package com.hfad.findingfalconeapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.MainActivity
import com.hfad.findingfalconeapplication.R
import com.hfad.findingfalconeapplication.data.Constants
import com.hfad.findingfalconeapplication.launchFragment
import com.hfad.findingfalconeapplication.showToast
import com.hfad.findingfalconeapplication.ui.adapter.MainAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    lateinit var tvPlanets: TextView
    lateinit var tvehicles: TextView
    lateinit var btnSubmit: Button
    lateinit var rvPlanets: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        tvPlanets = view.findViewById(R.id.tvPlanets)
        tvehicles = view.findViewById(R.id.tvehicles)
        btnSubmit = view.findViewById(R.id.btnSubmit)
        rvPlanets = view.findViewById(R.id.rvPlanets)
        tvPlanets.setOnClickListener {
            Constants.launchFlags = 0
            if (Constants.planetList.size>4 || Constants.vehiclList.size>4 ){
                (activity as MainActivity).showToast("Exceeding the Maximum Limit")
                return@setOnClickListener
            }
            (activity as MainActivity).launchFragment(PlanetsFragment(), R.id.frameId)
        }
        tvehicles.text = "No Planets Or Vehicles Selected"
        rvPlanets.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        if (Constants.planetList.size > 1) {
            init()
            btnSubmit.visibility = View.VISIBLE
        }
    }

    private fun init() {

        rvPlanets.adapter = MainAdapter(
            vehiclList = Constants.vehiclList,
            planetList = Constants.planetList,
            { pos ->
                if(pos<Constants.planetList.size){Constants.planetList.removeAt(pos)}
                if(pos<Constants.vehiclList.size){Constants.vehiclList.removeAt(pos)}

            })
        btnSubmit.setOnClickListener {
            if (Constants.planetList.size != 4) {
                (activity as MainActivity).showToast("Planets Should be equals to 4")
                return@setOnClickListener
            }
            if (Constants.vehiclList.size != 4) {
                (activity as MainActivity).showToast("Vehicles Should be equals to 4")
                return@setOnClickListener
            }
            Constants.completeData.planets = Constants.planetList
            Constants.completeData.vehicles = Constants.vehiclList
            GlobalScope.launch {
                (activity as MainActivity).planetsViewmodel.findPlanet(Constants.completeData,
                    { data ->
                        (activity as MainActivity).showToast(data)
                    },
                    { error -> (activity as MainActivity).showToast(error) })
            }
        }

        var names = ""
        var vnames = ""
        if (Constants.planetList.size > 1) {

            Constants.planetList.distinct()
                .forEach { planet -> names += "  ${planet}  " }


        }
        if (Constants.vehiclList.size > 1) {
            Constants.vehiclList.distinct().forEach { vehicle -> vnames += "  ${vehicle}  " }
        }
        tvehicles.text = "Planets : ${names}  & Vehicles : $vnames"
    }

}