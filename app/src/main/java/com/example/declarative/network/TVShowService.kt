package com.example.declarative.network

import com.example.declarative.model.TVShowDetails
import com.example.declarative.model.TVShowPopular
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TVShowService {
    @GET("api/most-popular")
    suspend fun apiTVShowPopular(@Query("page") page: Int): Response<TVShowPopular>

    @GET("api/show-details")
    suspend fun apiTVShowDetails(@Query("q") q: Int): Response<TVShowDetails>

}