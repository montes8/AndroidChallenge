package com.challenge.androidchallenge.repository.api

import com.challenge.androidchallenge.repository.BaseNetwork
import com.challenge.androidchallenge.repository.ServiceApi
import com.challenge.androidchallenge.usecases.repository.IAppRepositoryNetwork
import org.koin.core.inject


class AppNetwork : IAppRepositoryNetwork, BaseNetwork(){

    private val serviceApi: ServiceApi by inject()

    override suspend fun loadSearchData(query : String): Boolean {
        return executeWithConnection {
            serviceApi.getPlace("-11.9556891,-77.0025089",query)
            true
        }
    }
}