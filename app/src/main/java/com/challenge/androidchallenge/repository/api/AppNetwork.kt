package com.challenge.androidchallenge.repository.api

import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.repository.BaseNetwork
import com.challenge.androidchallenge.repository.ServiceApi
import com.challenge.androidchallenge.repository.exception.GenericException
import com.challenge.androidchallenge.repository.model.PlaceContentResponse
import com.challenge.androidchallenge.repository.toCompleteErrorModel
import com.challenge.androidchallenge.repository.utils.generalErrorMessage
import com.challenge.androidchallenge.repository.validateBody
import com.challenge.androidchallenge.usecases.repository.IAppRepositoryNetwork
import org.koin.core.inject


class AppNetwork : IAppRepositoryNetwork, BaseNetwork(){

    private val serviceApi: ServiceApi by inject()

    override suspend fun loadSearchData(query : String): List<PlacesModel> {
        return executeWithConnection {
            val response = serviceApi.getPlace("-11.9556891,-77.0025089",query)
            var param : List<PlacesModel> = ArrayList()
            if (response.isSuccessful && response.body() != null) {
                param = PlaceContentResponse.toContentPlaces(response.validateBody())
            }else response.errorBody()?.apply {
                throw
                    this.toCompleteErrorModel()?.getException() ?: Exception()
            } ?: throw GenericException(generalErrorMessage)

            param
        }
    }
}