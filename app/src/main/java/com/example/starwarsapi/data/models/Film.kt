package com.example.starwarsapi.data.models
import java.util.*
import kotlin.collections.ArrayList

data class Film(val title: String,
                val episode_id: Int,
                val director: String,
                val opening_crawl: String,
                val characters: ArrayList<String>,
                val planets: ArrayList<String>,
                val starships: ArrayList<String>,
                val species: ArrayList<String>,
                val release_date : Date)