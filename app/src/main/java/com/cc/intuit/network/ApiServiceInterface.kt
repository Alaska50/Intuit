package com.cc.intuit.network

import com.cc.intuit.models.ImageData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("list")
    suspend fun getData(@Query("page") page:Int, @Query("limit") limit:Int): List<ImageData>

}