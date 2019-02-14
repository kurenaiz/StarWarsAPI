package com.example.starwarsapi.data.models

data class Film(val title: String,val episode_id: Int, val director: String,val opening_crawl: String, val characters: List<String>)