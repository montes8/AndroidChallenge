package com.challenge.androidchallenge.repository.model

import com.google.gson.annotations.SerializedName

class MainResponse(
    @SerializedName("latitude")
    var latitude: Double? = 0.0,
    @SerializedName("longitude")
    var longitude: Double?= 0.0,
)