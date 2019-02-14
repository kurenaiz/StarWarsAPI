package com.example.starwarsapi

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.starwarsapi.adapters.MainAdapter
import com.example.starwarsapi.data.models.StarWarsCatalog
import com.example.starwarsapi.data.services.StarWarsService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val TAG = "MainThread"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView_main.layoutManager = LinearLayoutManager(this)

        fetchJsonRequest()
    }

    fun fetchJsonRequest(){
        val retrofit = Retrofit.Builder()
            .baseUrl(StarWarsService.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : StarWarsService = retrofit.create<StarWarsService>(StarWarsService::class.java)
        val requestCatalog : Call<StarWarsCatalog> = service.listCatalog()
        requestCatalog.enqueue(object: Callback<StarWarsCatalog?> {
            override fun onResponse(call: Call<StarWarsCatalog?>?,
                                    response: Response<StarWarsCatalog?>?) {
                if(!response?.isSuccessful!!) {
                    Log.d(TAG,"Error Response:" + response?.code())
                }
                else {
                    val catalog : StarWarsCatalog? = response?.body()

                    runOnUiThread{
                        recyclerView_main.adapter = MainAdapter(catalog!!)
                    }
                }
            }

            override fun onFailure(call: Call<StarWarsCatalog?>?,
                                   t: Throwable?) {
                Log.e(TAG,"Error Failure:" + t?.message)
            }
        })
    }
}
