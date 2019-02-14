package com.example.starwarsapi.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.StarWarsCatalog
import com.squareup.picasso.Picasso
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
        val filmTitleTextView = catalog.results.get(position)
        holder?.view?.textView_main_filmTitle?.text = filmTitleTextView.title

        val posterImageView = holder?.view?.imageView_main_filmImage
        when(catalog.results.get(position).episode_id){
            1 -> posterImageView.setImageResource(R.drawable.starwars_ep1_poster)
            2 -> posterImageView.setImageResource(R.drawable.starwars_ep2_poster)
            3 -> posterImageView.setImageResource(R.drawable.starwars_ep3_poster)
            4 -> posterImageView.setImageResource(R.drawable.starwars_ep4_poster)
            5 -> posterImageView.setImageResource(R.drawable.starwars_ep5_poster)
            6 -> posterImageView.setImageResource(R.drawable.starwars_ep6_poster)
            7 -> posterImageView.setImageResource(R.drawable.starwars_ep7_poster)
        }
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
    init {
        view.setOnClickListener {

        }
    }
}