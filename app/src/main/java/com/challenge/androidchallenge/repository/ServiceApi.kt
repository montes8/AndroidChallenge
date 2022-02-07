package com.challenge.androidchallenge.repository

import com.challenge.androidchallenge.repository.model.PlaceContentResponse
import retrofit2.Response
import retrofit2.http.*

interface ServiceApi {

    @GET("places/nearby")
    suspend fun getPlace(@Query("ll") ll : String,
                         @Query("query") query : String): Response<PlaceContentResponse>
}