package com.hfad.findingfalconeapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.findingfalconeapplication.R

class MainAdapter(var vehiclList:List<String>,
                  var planetList:List<String>,val pos:(Int)->Unit):RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var tvPlanet = itemView.findViewById<TextView>(R.id.tvPlanet)
        var tvVehicles = itemView.findViewById<TextView>(R.id.tvVehicles)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mainview_row,parent,false))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.apply {
            if (position<planetList.size ){tvPlanet.text=planetList[position]?:""}
            if (position<vehiclList.size){tvVehicles.text=vehiclList[position]?:""}
            tvVehicles.setOnClickListener {
                pos(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
       return if (vehiclList.size>planetList.size) vehiclList.size
        else planetList.size
    }
}