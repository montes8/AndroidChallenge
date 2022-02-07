package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.entity.LocationModel
import com.challenge.androidchallenge.repository.utils.EMPTY
import com.google.gson.annotations.SerializedName

class LocationResponse(
    @SerializedName("address")
    var address: String? = EMPTY,
    @SerializedName("country")
    var country: String?= EMPTY,
    @SerializedName("cross_street")
    var cross_street: String?= EMPTY,
    @SerializedName("locality")
    var locality: String?= EMPTY,
    @SerializedName("region")
    var region: String?= EMPTY,
){
    fun toLocation()= LocationModel(address?: EMPTY,country?: EMPTY,
    cross_street?: EMPTY,locality?: EMPTY,region?: EMPTY)

    companion object{
        fun toLocationDefault(response :LocationResponse )= LocationModel(response.address?: EMPTY,
            response.country?: EMPTY, response.cross_street?: EMPTY,
            response.locality?: EMPTY,response.region?: EMPTY)
    }
}