package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.entity.PlacesModel
import com.google.gson.annotations.SerializedName

class PlaceContentResponse(
    @SerializedName("results")
    var results: List<PlacesResponse>
){
    companion object{
        fun toListPlaces(response :List<PlacesResponse> ):List<PlacesModel> = response.map {
            it.toPlace()
        }

        fun toContentPlaces(response :PlaceContentResponse ):List<PlacesModel> {
            return toListPlaces(response.results)
        }
    }
}