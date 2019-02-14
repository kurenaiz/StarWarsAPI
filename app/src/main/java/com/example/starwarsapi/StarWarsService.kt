package com.example.starwarsapi
import com.example.starwarsapi.models.StarWarsCatalog
import retrofit2.Call
import retrofit2.http.GET

interface StarWarsService
{
    companion object {
        const val URL_BASE: String = "https://swapi.co/api/"
    }

    @GET("films/?format=json")
    fun listCatalog(): Call<StarWarsCatalog>
}