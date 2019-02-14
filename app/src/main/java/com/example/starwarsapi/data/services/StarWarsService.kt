package com.example.starwarsapi.data.services
import com.example.starwarsapi.data.models.StarWarsCatalog
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsService
{
    @GET("films/?format=json")
    fun listCatalog(): Call<StarWarsCatalog>

    companion object {
        const val URL_BASE: String = "https://swapi.co/api/"
    }
}