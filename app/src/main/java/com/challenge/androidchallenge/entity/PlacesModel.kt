package com.challenge.androidchallenge.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class PlacesModel(
    var fsq_id: String,
    var categories: List<CategoryModel>,
    @SerializedName("distance")
    var distance: String,
    var geocodes: GeocodeModel,
    var location: LocationModel,
    var name: String,
    var timezone: String
):Serializable