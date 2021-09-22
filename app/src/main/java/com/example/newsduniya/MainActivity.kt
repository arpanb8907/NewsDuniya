package com.example.newsduniya

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), newitemclicked {

    private lateinit var madapter : newsadapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView.layoutManager = LinearLayoutManager(this)
        getdata()
        madapter = newsadapter(this) // create adapter instance

        // link adapter and recyclerview
        recyclerView.adapter = madapter




    }

    private fun getdata(){
        val url = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            {

                val newsJsonArray = it.getJSONArray("articles")
                val newsarray = ArrayList<news_data>()
                for(i in 0 until newsJsonArray.length()){
                    val newsobject = newsJsonArray.getJSONObject(i)
                    val news = news_data(
                        newsobject.getString("title"),
                        newsobject.getString("author"),
                        newsobject.getString("urlToImage"),
                        newsobject.getString("url")

                    )
                    newsarray.add(news)
                }
                madapter.updatednews(newsarray)
            },
            {  })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onitemclick(item: news_data) {


        val builder =  CustomTabsIntent.Builder()
        val customTabsIntent  = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))

    }
}
