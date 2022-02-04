package com.challenge.androidchallenge.usecases.usecase

import com.challenge.androidchallenge.usecases.repository.IAppRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject


class AppUseCase : KoinComponent {

    private val iAppRepositoryNetwork: IAppRepositoryNetwork by inject()

    suspend fun loadSearchData() = iAppRepositoryNetwork.loadSearchData()

}