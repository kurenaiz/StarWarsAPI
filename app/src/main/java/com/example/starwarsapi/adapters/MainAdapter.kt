package com.example.starwarsapi.adapters

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.starwarsapi.R
import com.example.starwarsapi.data.models.StarWarsCatalog
import com.example.starwarsapi.detail.PeopleActivity
import com.example.starwarsapi.detail.PlanetActivity
import com.example.starwarsapi.detail.SpecieActivity
import com.example.starwarsapi.detail.StarshipActivity
import kotlinx.android.synthetic.main.film_row.view.*

import java.util.*

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
        //setting film title
        val filmTitleText = catalog.results.get(position).title
        holder?.view?.textView_main_filmTitle?.text = filmTitleText

        //setting film description
        val filmDescriptionText =  catalog.results.get(position).opening_crawl
        holder?.view?.textView_main_filmDescription?.text = String.format("""%s""",filmDescriptionText)

        //setting film director
        val filmDirectorText = catalog.results.get(position).director
        holder?.view?.textView_main_filmDirector.text = filmDirectorText

        //setting film release date
        val filmReleaseDateText = catalog.results.get(position).release_date
        holder?.view?.textView_main_filmReleaseDate.text = formattedDate(filmReleaseDateText)

        //setting film posters
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

        //passing a list of url for reference
        holder?.peoples_url_list = catalog.results.get(position).characters
        holder?.planets_url_list = catalog.results.get(position).planets
        holder?.species_url_list = catalog.results.get(position).species
        holder?.starships_url_list = catalog.results.get(position).starships
    }

    //returns the year of date
    fun formattedDate(date : Date): String{
        val cal = Calendar.getInstance()
        cal.setTime(date)
        val year = cal.get(Calendar.YEAR)
        return year.toString()
    }

}

class CustomViewHolder(val view: View,
                       var peoples_url_list : ArrayList<String>? = null,
                       var planets_url_list : ArrayList<String>? = null,
                       var species_url_list : ArrayList<String>? = null,
                       var starships_url_list : ArrayList<String>? = null): RecyclerView.ViewHolder(view){

    companion object {
        val PEOPLE_URL_KEY = "PEOPLES_URL"
        val PLANET_URL_KEY = "PLANET_URL"
        val SPECIE_URL_KEY = "SPECIE_URL"
        val STARSHIP_URL_KEY = "STARSHIP_URL"
    }

    init {
        //adding listeners for each button in the row
        view.button_main_cast.setOnClickListener {
            val intent = Intent(view.context,PeopleActivity::class.java)
            //intent.putExtra(CAST_TITLE_KEY, "")
            intent.putExtra(PEOPLE_URL_KEY, peoples_url_list)
            view.context.startActivity(intent)
        }

        view.button_main_planets.setOnClickListener {
            val intent = Intent(view.context,PlanetActivity::class.java)
            //intent.putExtra(PLANET_TITLE_KEY, "")
            intent.putExtra(PLANET_URL_KEY, planets_url_list)
            view.context.startActivity(intent)
        }

        view.button_main_species.setOnClickListener {
            val intent = Intent(view.context,SpecieActivity::class.java)
            //intent.putExtra(SPECIE_TITLE_KEY, "")
            intent.putExtra(SPECIE_URL_KEY, species_url_list)
            view.context.startActivity(intent)
        }

        view.button_main_starships.setOnClickListener {
            val intent = Intent(view.context,StarshipActivity::class.java)
            //intent.putExtra(STARSHIP_TITLE_KEY, "")
            intent.putExtra(STARSHIP_URL_KEY, starships_url_list)
            view.context.startActivity(intent)
        }
    }
}