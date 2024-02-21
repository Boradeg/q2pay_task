package com.example.apiretrofit.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apiretrofit.Adapter.ApiInterface
import com.example.apiretrofit.Adapter.MyAdapter
import com.example.apiretrofit.DataClass.MyData

import com.example.apiretrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val retrofitBuilder = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(ApiInterface::class.java)
        val retrofitData = retrofitBuilder.getProductData()
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                //if api call is success
                var responseBody = response.body()
                val productList =
                    responseBody?.products!!
                myAdapter = MyAdapter(this@MainActivity, productList)
                binding.rv.adapter = myAdapter
                binding.rv.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                //if api call is fails
                Log.d("mainActivity", "onFailure" + t.message)
            }
        })
    }
}