package com.hfad.findingfalconeapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.R
import com.hfad.findingfalconeapplication.data.model.NewPlanets
import com.hfad.findingfalconeapplication.data.model.Planets
import java.util.ArrayList

class PlanetsAdapter(val onClick: (NewPlanets,Int) -> Unit) :
    RecyclerView.Adapter<PlanetsAdapter.PlanetsViewHolder>() {
    var planets: ArrayList<NewPlanets> = arrayListOf()
    var flag=0

    inner class PlanetsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvPlanet = itemView.findViewById<TextView>(R.id.tvPlanet)
        var tvDistance = itemView.findViewById<TextView>(R.id.tvDistance)
        val ivPlanet = itemView.findViewById<ImageView>(R.id.ivPlanet)
        val ivCheck = itemView.findViewById<ImageView>(R.id.ivCheck)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetsViewHolder =
        PlanetsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.planets_row, parent, false)
        )
fun setPlanets(list: List<NewPlanets>){
    this.planets= list as ArrayList<NewPlanets>
    notifyDataSetChanged()
}
    override fun onBindViewHolder(holder: PlanetsViewHolder, position: Int) {

        holder.apply {
            planets[position].let {
                tvPlanet.text = it.name
                tvDistance.text = "Distance : ${it.distance}"
                ivPlanet.setImageResource(it.image)
            }
            itemView.setOnClickListener {
                if (ivCheck.isVisible){
                    ivCheck.visibility=View.GONE
                    planets[position].visibility=false
                }else{
                    ivCheck.visibility=View.VISIBLE
                    planets[position].visibility=true
                }
                onClick(planets[position],position)
            }

            ivCheck.isVisible=planets[position].visibility
        }
    }

    override fun getItemCount(): Int = planets.size
}