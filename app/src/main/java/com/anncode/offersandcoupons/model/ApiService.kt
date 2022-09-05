package com.anncode.offersandcoupons.model

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    //Pide información al web service a este endpoint
    @GET("getOffers/")
    fun getCoupons(): Call<JsonObject>
}