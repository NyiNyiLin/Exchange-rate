package com.nyi.exchangerate.services

import com.nyi.exchangerate.responses.ExchangeRateResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    companion object Factory{
        val baseURL = "http://forex.cbm.gov.mm/api/"

        fun create(): ApiService{

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }


    @GET("history/{date}")
    fun getRateByDate(
        @Path("date") date : String
    ) : Call<ExchangeRateResponse>



}