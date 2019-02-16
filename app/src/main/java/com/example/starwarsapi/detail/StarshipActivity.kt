package com.example.starwarsapi.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast

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
    private val starshipAdapter = StarshipAdapter()

    var rowNumber : Int = 0
    var urlList : MutableList<String>? = null
    var starshipList : MutableList<Starship> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        recyclerView_detail.layoutManager = LinearLayoutManager(this)
        recyclerView_detail.adapter = starshipAdapter

        supportActionBar?.title = "Starships"

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
                        Toast.makeText(applicationContext, "Falha em obter uma resposta do Request", Toast.LENGTH_SHORT).show()
                    } else {
                        val people : Starship? = response?.body()
                        starshipList?.add(people!!)
                        rowNumber++

                        starshipAdapter.starshipList = starshipList
                    }
                }

                override fun onFailure(call: Call<Starship?>?,
                                       t: Throwable?) {
                    Toast.makeText(applicationContext, "Falha no Request", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}
