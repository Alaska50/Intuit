package com.cc.intuit.models

import com.squareup.moshi.Json

data class ImageData (

    @Json(name = "author")
    val author: String,
    @Json(name = "download_url")
    val downloadURL: String

        )