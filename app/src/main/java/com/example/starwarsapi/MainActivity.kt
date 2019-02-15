package com.example.starwarsapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.example.starwarsapi.adapters.MainAdapter
import com.example.starwarsapi.data.DataSource
import com.example.starwarsapi.data.models.StarWarsCatalog

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val TAG = "Main-Activity"

    private val dataSource = DataSource
    private val mainAdapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView_main.adapter = mainAdapter
        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJsonRequest()
    }

    fun fetchJsonRequest(){
        dataSource.getFilms().enqueue(object: Callback<StarWarsCatalog?> {
            override fun onResponse(call: Call<StarWarsCatalog?>?,
                                    response: Response<StarWarsCatalog?>?) {
                if(!response?.isSuccessful!!) {
                    Toast.makeText(applicationContext, "Falha em obter uma resposta do Request", Toast.LENGTH_SHORT).show()
                }
                else {
                    val catalog : StarWarsCatalog? = response?.body()

                    mainAdapter.catalog = catalog!!.results
                }
            }

            override fun onFailure(call: Call<StarWarsCatalog?>?,
                                   t: Throwable?) {
                Toast.makeText(applicationContext, "O Request falhou.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
