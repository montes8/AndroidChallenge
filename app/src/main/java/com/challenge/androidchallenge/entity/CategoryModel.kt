package com.challenge.androidchallenge.entity

import java.io.Serializable

data class CategoryModel(
    var id: String,
    var name: String,
    var icon: IconModel
) : Serializable