package com.example.starwarsapi.data

import com.example.starwarsapi.data.models.*
import com.example.starwarsapi.data.services.StarWarsService
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataSource {
    var service : StarWarsService
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(StarWarsService.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create<StarWarsService>(StarWarsService::class.java)
        val request : Call<StarWarsCatalog> = service.listCatalog()
    }

    fun getFilms() = service.listCatalog()

    fun getPeople(url : String) = service.getCharacter(url)

    fun getPlanet(url : String) = service.getPlanet(url)

    fun getSpecie(url : String) = service.getSpecie(url)

    fun getStarship(url : String) = service.getStarship(url)
}