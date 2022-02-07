package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.entity.GeocodeModel
import com.challenge.androidchallenge.entity.MainModel
import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.repository.utils.EMPTY
import com.google.gson.annotations.SerializedName

class PlacesResponse(
    @SerializedName("fsq_id")
    var fsq_id: String?,
    @SerializedName("categories")
    var categories: List<CategoryResponse>?,
    @SerializedName("distance")
    var distance: String?,
    @SerializedName("geocodes")
    var geocodes: GeocodeResponse?,
    @SerializedName("location")
    var location: LocationResponse?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("timezone")
    var timezone: String?
){

    fun toPlace()= PlacesModel(fsq_id?: EMPTY,CategoryResponse.toListCategory(categories?:ArrayList()),
    distance?: EMPTY,geocodes?.toGeocode()?: GeocodeModel(MainModel(0.0,0.0)),
        location?.toLocation()?: LocationResponse.toLocationDefault(LocationResponse()),
        name?: EMPTY,timezone?: EMPTY
    )
}