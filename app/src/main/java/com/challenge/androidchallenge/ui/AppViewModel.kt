package com.challenge.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.androidchallenge.usecases.usecase.AppUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppViewModel : BaseViewModel(), KoinComponent {

    val successPlacesLiveData        : LiveData<Boolean> get()   =_successPlacesLiveData
    private val _successPlacesLiveData    = MutableLiveData<Boolean>()

    private val appUseCase: AppUseCase by inject()

    fun getPlaces(query : String){
            executeSuspendNotProgress {
                val response = appUseCase.loadSearchData(query)
                _successPlacesLiveData.postValue(response)
            }
    }

}