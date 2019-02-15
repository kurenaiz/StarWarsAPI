package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.Starship
import kotlinx.android.synthetic.main.detail_row.view.*

class StarshipAdapter(val starshipList : List<Starship>?) : RecyclerView.Adapter<StarshipViewHolder>() {

    override fun getItemCount(): Int {
        return starshipList!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarshipViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val customView = layoutInflater.inflate(R.layout.detail_row,parent,false)
        return StarshipViewHolder(customView)
    }

    override fun onBindViewHolder(holder: StarshipViewHolder, position: Int) {
        val starshipName = starshipList?.get(position)?.name
        holder?.view?.textView_detail_name?.text = starshipName
    }
}

class StarshipViewHolder(val view : View): RecyclerView.ViewHolder(view){

}