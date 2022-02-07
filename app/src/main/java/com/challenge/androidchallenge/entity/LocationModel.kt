package com.challenge.androidchallenge.entity

import java.io.Serializable

class LocationModel(
    var address: String,
    var country: String,
    var cross_street: String,
    var locality: String,
    var region: String,
): Serializable