package com.example.apiretrofit.Adapter

import com.example.apiretrofit.DataClass.MyData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("product")
    fun getProductData():Call<MyData>
}
