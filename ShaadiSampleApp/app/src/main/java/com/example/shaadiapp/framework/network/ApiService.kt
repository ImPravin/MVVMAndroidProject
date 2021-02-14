package com.example.shaadiapp.framework.network

import com.example.shaadiapp.domain.entities.RequestResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("api/")
    suspend fun getAllRequests(@Query("results") count: Int): RequestResult
}