package com.challenge.androidchallenge.usecases.repository

import com.challenge.androidchallenge.entity.PlacesModel


interface IAppRepositoryNetwork {
     suspend fun loadSearchData(query : String):List<PlacesModel>
}