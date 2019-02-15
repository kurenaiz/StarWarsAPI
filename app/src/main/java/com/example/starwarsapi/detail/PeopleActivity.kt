package com.example.starwarsapi.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log

import com.example.starwarsapi.R
import com.example.starwarsapi.adapters.CustomViewHolder
import com.example.starwarsapi.adapters.PeopleAdapter
import com.example.starwarsapi.data.DataSource
import com.example.starwarsapi.data.models.People

import kotlinx.android.synthetic.main.activity_details.*

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleActivity : AppCompatActivity() {
    val TAG = "People-Activity"

    private val dataSource = DataSource

    var rowNumber : Int = 0
    var urlList : ArrayList<String>? = null
    var peopleList : ArrayList<People> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        recyclerView_detail.layoutManager = LinearLayoutManager(this)

        urlList = intent.getStringArrayListExtra(CustomViewHolder.PEOPLE_URL_KEY)
        fetchMore()

        //request the next row
        button_detail_more.setOnClickListener {
            fetchMore()
        }
    }

    fun fetchMore() {
        if(urlList!!.count() > rowNumber) {
            var url = urlList?.get(rowNumber)
            dataSource.getPeople(url!!).enqueue(object : Callback<People?> {
                override fun onResponse(call: Call<People?>?,
                                        response: Response<People?>?) {
                    if (!response?.isSuccessful!!) {
                        Log.d(TAG, "Error Response:" + response?.code())
                    } else {
                        val people: People? = response?.body()
                        peopleList?.add(people!!)
                        rowNumber++

                        recyclerView_detail.adapter = PeopleAdapter(peopleList!!)
                    }
                }

                override fun onFailure(call: Call<People?>?,
                                       t: Throwable?) {
                    Log.e(TAG, "Error Failure:" + t?.message)
                }
            })
        }
    }
}