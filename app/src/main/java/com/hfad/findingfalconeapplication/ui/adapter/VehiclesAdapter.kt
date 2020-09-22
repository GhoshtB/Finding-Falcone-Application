package com.hfad.findingfalconeapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.R
import com.hfad.findingfalconeapplication.data.model.NewVehicles
import com.hfad.findingfalconeapplication.data.model.Vehicles
import java.util.*

class VehiclesAdapter(val onClick: (NewVehicles,Int) -> Unit):RecyclerView.Adapter<VehiclesAdapter.VehiclesHolder>() {
    var vehicles: ArrayList<NewVehicles> = arrayListOf()
    var flag=0
    var cnt=0

    inner class VehiclesHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var tvPlanet = itemView.findViewById<TextView>(R.id.tvPlanet)
        var tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        val ivPlanet = itemView.findViewById<ImageView>(R.id.ivPlanet)
        val ivCheck = itemView.findViewById<ImageView>(R.id.ivCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiclesHolder =
        VehiclesHolder( LayoutInflater.from(parent.context).inflate(R.layout.planets_row, parent, false))

    fun setPlanets(list: List<NewVehicles>){
        this.vehicles= list as ArrayList<NewVehicles>
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: VehiclesHolder, position: Int) {
        vehicles[position].let {
            holder.apply {
                tvPlanet.text=it.name
                tvDistance.text=" Max Distance: ${it.max_distance}   Speed: ${it.speed}   Total No: ${it.total_no}"
                if (it.name.equals("Space pod")){
                    ivPlanet.setImageResource(R.drawable.ic_ufo)
                }
               else if (it.name.equals("Space rocket")){
                    ivPlanet.setImageResource(R.drawable.ic_rocket_ship)
                }
               else if (it.name.equals("Space shuttle")){
                    ivPlanet.setImageResource(R.drawable.ic_space_shuttle)
                }
               else if (it.name.equals("Space ship")){
                    ivPlanet.setImageResource(R.drawable.ic_spaceship)
                }
                itemView.setOnClickListener {
                    if (ivCheck.isVisible){
                        ivCheck.visibility=View.GONE

                    }else{
                        ivCheck.visibility=View.VISIBLE
                    }
                    onClick(vehicles[position],position)
                }

                ivCheck.isVisible=it.visibility
            }
        }
    }

    override fun getItemCount(): Int =vehicles.size
}