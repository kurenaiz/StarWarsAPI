package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.Specie
import kotlinx.android.synthetic.main.detail_row.view.*

class SpecieAdapter(val specieList : List<Specie>?) : RecyclerView.Adapter<SpecieViewHolder>() {

    override fun getItemCount(): Int {
        return specieList!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecieViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val customView = layoutInflater.inflate(R.layout.detail_row,parent,false)
        return SpecieViewHolder(customView)
    }

    override fun onBindViewHolder(holder: SpecieViewHolder, position: Int) {
        val specieName = specieList?.get(position)?.name
        holder?.view?.textView_detail_name?.text = specieName
    }
}

class SpecieViewHolder(val view : View): RecyclerView.ViewHolder(view){

}