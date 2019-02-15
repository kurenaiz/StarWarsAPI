package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.People
import kotlinx.android.synthetic.main.detail_row.view.*

class PeopleAdapter(val peopleList : List<People>?) : RecyclerView.Adapter<PeopleViewHolder>() {

    override fun getItemCount(): Int {
        return peopleList!!.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val customView = layoutInflater.inflate(R.layout.detail_row,parent,false)
        return PeopleViewHolder(customView)
    }

    override fun onBindViewHolder(holder: PeopleViewHolder, position: Int) {
        val chararacterName = peopleList?.get(position)?.name
        holder?.view?.textView_detail_name?.text = chararacterName
    }
}

class PeopleViewHolder(val view : View): RecyclerView.ViewHolder(view){

}