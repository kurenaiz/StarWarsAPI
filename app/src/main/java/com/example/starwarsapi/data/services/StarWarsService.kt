package com.example.starwarsapi.data.services
import com.example.starwarsapi.data.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface StarWarsService
{
    @GET("films/?format=json")
    fun listCatalog(): Call<StarWarsCatalog>

    @GET
    fun getCharacter(@Url url : String): Call<People>

    @GET
    fun getPlanet(@Url url : String): Call<Planet>

    @GET
    fun getSpecie(@Url url : String): Call<Specie>

    @GET
    fun getStarship(@Url url : String): Call<Starship>

    companion object {
        const val URL_BASE: String = "https://swapi.co/api/"
    }
}