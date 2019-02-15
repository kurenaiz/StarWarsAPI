package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.Planet
import kotlinx.android.synthetic.main.detail_row.view.*

class PlanetAdapter(val planetList : List<Planet>?) : RecyclerView.Adapter<PlanetViewHolder>() {

    override fun getItemCount(): Int {
        return planetList!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanetViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val customView = layoutInflater.inflate(R.layout.detail_row,parent,false)
        return PlanetViewHolder(customView)
    }

    override fun onBindViewHolder(holder: PlanetViewHolder, position: Int) {
        val planetName = planetList?.get(position)?.name
        holder?.view?.textView_detail_name?.text = planetName
    }
}

class PlanetViewHolder(val view : View): RecyclerView.ViewHolder(view){

}