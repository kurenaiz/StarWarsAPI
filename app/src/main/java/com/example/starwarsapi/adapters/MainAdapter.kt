package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.StarWarsCatalog
import kotlinx.android.synthetic.main.film_row.view.*

class MainAdapter(val catalog : StarWarsCatalog) : RecyclerView.Adapter<CustomViewHolder>() {
    override fun getItemCount(): Int {
        return catalog.results.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.film_row, parent,false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val videoTile = catalog.results.get(position)
        holder?.view?.textView_main_filmTitle?.text = videoTile.title
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}