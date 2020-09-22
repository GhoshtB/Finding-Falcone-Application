package com.hfad.findingfalconeapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.MainActivity
import com.hfad.findingfalconeapplication.R
import com.hfad.findingfalconeapplication.data.Constants
import com.hfad.findingfalconeapplication.data.model.NewVehicles
import com.hfad.findingfalconeapplication.showToast
import com.hfad.findingfalconeapplication.ui.adapter.VehiclesAdapter


class VehicleFragment : Fragment() {

    lateinit var vehiclesAdapter: VehiclesAdapter
    lateinit var rvPlanets: RecyclerView
    lateinit var btnSubmit: Button
    val list = arrayListOf<String>()
    var vehicless: List<NewVehicles> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_vehicle, container, false)
        rvPlanets = view.findViewById(R.id.rvPlanets)
        btnSubmit = view.findViewById(R.id.btnSubmit)


        init()
        return view
    }

    private fun init() {
        vehiclesAdapter = VehiclesAdapter({ vehicle, pos ->

            if (list.size < 1) {
                list.add(vehicle.name)

            } else {
//                (activity as MainActivity).showToast("Cant Select more Than One")
                list.set(0, vehicle.name)
                vehicless.forEach { vehicles ->
                    if (vehicles.name == vehicle.name) {
                        vehicles.visibility = true
                    } else {
                        vehicles.visibility = false
                    }
                }
                vehiclesAdapter.setPlanets(vehicless)
            }


        })
        rvPlanets.apply {
            layoutManager = GridLayoutManager(activity!!.applicationContext, 2)
            setHasFixedSize(true)
            adapter = vehiclesAdapter
        }

        (activity as MainActivity).planetsViewmodel.vehiclesData.observe(
            viewLifecycleOwner,
            Observer { vehicles ->
                vehicless = vehicles.map { vehicle ->
                    NewVehicles(
                        vehicle.name,
                        vehicle.total_no,
                        vehicle.max_distance,
                        vehicle.speed,
                        false
                    )
                }
                vehiclesAdapter.setPlanets(vehicless)

            })

        btnSubmit.setOnClickListener {
                Constants.vehiclList.addAll(list)

            if (list.size == 1) {
                (activity as MainActivity).supportFragmentManager.popBackStack()
                onBackPressed()
                (activity as MainActivity).showToast(" Vehicle & Planet Got Updated Succesfully")
            } else {
                (activity as MainActivity).showToast("Please Select Vehicle")
            }
        }
    }

    fun onBackPressed() {
        val fragmentManager: FragmentManager? = fragmentManager
        fragmentManager!!.popBackStack(
            fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2)
                .getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
    }

}