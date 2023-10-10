package com.realllapp.jobber.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    val api: JobberApi by lazy {
        Retrofit.Builder()
            .baseUrl(RetrofitUrls.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JobberApi::class.java)
    }
}