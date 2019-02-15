package com.example.starwarsapi.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import com.example.starwarsapi.R
import com.example.starwarsapi.adapters.CustomViewHolder
import com.example.starwarsapi.adapters.PlanetAdapter
import com.example.starwarsapi.data.DataSource
import com.example.starwarsapi.data.models.Planet

import kotlinx.android.synthetic.main.activity_details.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanetActivity : AppCompatActivity() {
    val TAG = "Planet-Activity"

    private val dataSource = DataSource

    var rowNumber : Int = 0
    var urlList : ArrayList<String>? = null
    var planetList : ArrayList<Planet> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        recyclerView_detail.layoutManager = LinearLayoutManager(this)

        urlList = intent.getStringArrayListExtra(CustomViewHolder.PLANET_URL_KEY)
        fetchMore()

        //request the next row
        button_detail_more.setOnClickListener {
            fetchMore()
        }
    }

    fun fetchMore() {
        if(urlList!!.count() > rowNumber) {
            var url = urlList?.get(rowNumber)
            dataSource.getPlanet(url!!).enqueue(object : Callback<Planet?> {
                override fun onResponse(call: Call<Planet?>?,
                                        response: Response<Planet?>?) {
                    if (!response?.isSuccessful!!) {
                        Log.d(TAG, "Error Response:" + response?.code())
                    } else {
                        val planet: Planet? = response?.body()
                        planetList?.add(planet!!)
                        rowNumber++;

                        recyclerView_detail.adapter = PlanetAdapter(planetList!!)
                    }
                }

                override fun onFailure(call: Call<Planet?>?,
                                       t: Throwable?) {
                    Log.e(TAG, "Error Failure:" + t?.message)
                }
            })
        }
    }
}
