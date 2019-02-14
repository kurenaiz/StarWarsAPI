package com.example.starwarsapi

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.starwarsapi.models.StarWarsCatalog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

class MainActivity : AppCompatActivity()
{
    val TAG = "MainThread"

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl(StarWarsService.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service : StarWarsService = retrofit.create<StarWarsService>(StarWarsService::class.java)
        val requestCatalog : Call<StarWarsCatalog> = service.listCatalog()
        requestCatalog.enqueue(object: Callback<StarWarsCatalog?>
        {
            override fun onResponse(call: Call<StarWarsCatalog?>?,
                                    response: Response<StarWarsCatalog?>?)
            {
                if(!response?.isSuccessful!!)
                {
                    Log.i(TAG,"Error Response:" + response?.code())
                }
                else
                {
                    val catalog : StarWarsCatalog? = response?.body()
                    for(i in catalog?.results!!)
                       Log.i(TAG,String.format("%s: %s", i.title, i.director))
                }
            }

            override fun onFailure(call: Call<StarWarsCatalog?>?,
                                   t: Throwable?)
            {
                Log.e(TAG,"Error Failure:" + t?.message)
            }
        })
    }
}
