package com.example.starwarsapi.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import com.example.starwarsapi.R
import com.example.starwarsapi.adapters.CustomViewHolder
import com.example.starwarsapi.adapters.StarshipAdapter
import com.example.starwarsapi.data.DataSource
import com.example.starwarsapi.data.models.Starship

import kotlinx.android.synthetic.main.activity_details.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarshipActivity : AppCompatActivity() {
    val TAG = "Starship-Activity"

    private val dataSource = DataSource

    var rowNumber : Int = 0
    var urlList : ArrayList<String>? = null
    var starshipList : ArrayList<Starship> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        recyclerView_detail.layoutManager = LinearLayoutManager(this)

        urlList = intent.getStringArrayListExtra(CustomViewHolder.STARSHIP_URL_KEY)
        fetchMore()

        //request the next row
        button_detail_more.setOnClickListener {
            fetchMore()
        }
    }

    fun fetchMore() {
        if(urlList!!.count() > rowNumber) {
            var url = urlList?.get(rowNumber)
            dataSource.getStarship(url!!).enqueue(object: Callback<Starship?> {
                override fun onResponse(call: Call<Starship?>?,
                                        response: Response<Starship?>?) {
                    if (!response?.isSuccessful!!) {
                        Log.d(TAG,"Error Response:" + response?.code())
                    } else {
                        val people : Starship? = response?.body()
                        starshipList?.add(people!!)
                        rowNumber++

                        recyclerView_detail.adapter = StarshipAdapter(starshipList!!)
                    }
                }

                override fun onFailure(call: Call<Starship?>?,
                                       t: Throwable?) {
                    Log.e(TAG,"Error Failure:" + t?.message)
                }
            })
        }
    }
}
