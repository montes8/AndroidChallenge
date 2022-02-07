package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.repository.utils.EMPTY
import com.google.gson.annotations.SerializedName

class IconResponse(
    @SerializedName("prefix")
    var prefix: String? = EMPTY,
    @SerializedName("suffix")
    var suffix: String? = EMPTY
)