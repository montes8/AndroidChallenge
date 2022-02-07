package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.entity.GeocodeModel
import com.challenge.androidchallenge.entity.MainModel
import com.google.gson.annotations.SerializedName

class GeocodeResponse(
    @SerializedName("main")
    var main: MainResponse? = MainResponse()
){
    fun toGeocode()= GeocodeModel(MainModel(main?.latitude?:0.0,main?.longitude?:0.0))
}