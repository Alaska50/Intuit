package com.cc.intuit.network

import com.cc.intuit.models.ImageData
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.http.GET
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Query

private const val BASE_URL = "https://picsum.photos/v2/"
const val NETWORK_PAGE_SIZE = 20

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object AuthorAPI {
    val retrofitService : ApiServiceInterface by lazy {
        retrofit.create(ApiServiceInterface::class.java)
    }
}