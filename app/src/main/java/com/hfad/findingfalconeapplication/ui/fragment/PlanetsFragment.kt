package com.hfad.findingfalconeapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.MainActivity
import com.hfad.findingfalconeapplication.R
import com.hfad.findingfalconeapplication.data.Constants
import com.hfad.findingfalconeapplication.data.model.NewPlanets
import com.hfad.findingfalconeapplication.launchFragment
import com.hfad.findingfalconeapplication.showToast
import com.hfad.findingfalconeapplication.ui.adapter.PlanetsAdapter
import com.hfad.findingfalconeapplication.ui.adapter.VehiclesAdapter
import java.util.ArrayList


class PlanetsFragment : Fragment() {

    val planetsList = arrayListOf<NewPlanets>()
    lateinit var rvPlanets: RecyclerView
    lateinit var adapters: PlanetsAdapter
    lateinit var tvPlanets: TextView


    val images = intArrayOf(
        R.drawable.ic_donlon, R.drawable.ic_enchai, R.drawable.ic_jebing,
        R.drawable.ic_lerbin, R.drawable.ic_pingasor, R.drawable.ic_sapire,
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_planets, container, false)
        rvPlanets = view.findViewById(R.id.rvPlanets)
        tvPlanets = view.findViewById(R.id.tvPlanets)

        adapters = PlanetsAdapter({ planet,pos ->
            if (Constants.planetList.any { plane->planet.name==plane }){
                (activity as MainActivity).showToast("Already Added")
                return@PlanetsAdapter
            }
            if (Constants.planetList.size < 5) {
                Constants.planetList.add(planet.name)
            } else {
                Constants.planetList.set(0, planet.name)
            }
            (activity as MainActivity).launchFragment(VehicleFragment(),R.id.frameId)
        })

        rvPlanets.apply {
            layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
            setHasFixedSize(true)
                adapter = adapters
        }
        adapters.setPlanets(Constants.planets)
        init()
        return view
    }

    private fun init() {
        (activity as MainActivity).planetsViewmodel.planetsData.observe(
            viewLifecycleOwner,
            Observer { planetss ->
                var cnt = 0
                Constants.planets=planetss.map { planets ->
                    NewPlanets(
                        planets.name,
                        planets.distance,
                        images[cnt++],
                        false
                    )
                }

                    adapters.setPlanets(Constants.planets)


            })





    }

}