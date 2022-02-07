package com.challenge.androidchallenge.repository.model

import com.challenge.androidchallenge.entity.CategoryModel
import com.challenge.androidchallenge.entity.IconModel
import com.challenge.androidchallenge.repository.utils.EMPTY
import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("id")
    var id: String?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("icon")
    var icon: IconResponse?
) {
    fun toCategory()= CategoryModel(id?: EMPTY,name?: EMPTY,
        IconModel(icon?.prefix?: EMPTY,icon?.suffix?: EMPTY))

    companion object{
        fun toListCategory(response : List<CategoryResponse>) : List<CategoryModel> = response.map {
            it.toCategory()
        }
    }
}