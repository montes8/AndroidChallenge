package com.challenge.androidchallenge.usecases.repository


interface IAppRepositoryNetwork {
     suspend fun loadSearchData():Boolean
}