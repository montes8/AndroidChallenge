package com.challenge.androidchallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.challenge.androidchallenge.entity.ModelGeneric
import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.usecases.usecase.AppUseCase
import org.koin.core.KoinComponent
import org.koin.core.inject

class AppViewModel : BaseViewModel(), KoinComponent {

    val successPlacesLiveData        : LiveData<List<PlacesModel>> get()   =_successPlacesLiveData
    private val _successPlacesLiveData    = MutableLiveData<List<PlacesModel>>()

    private val appUseCase: AppUseCase by inject()

    fun getPlaces(query : String){
            executeSuspendNotProgress {
                val response = appUseCase.loadSearchData(query)
                _successPlacesLiveData.postValue(response)
            }
    }

     fun configDataGeneric(place : PlacesModel):List<ModelGeneric>{
         val list : ArrayList<ModelGeneric> = ArrayList()
         list.add(ModelGeneric("Dirección",place.location.address))
         list.add(ModelGeneric("Pais",place.location.country))
         list.add(ModelGeneric("Avenida",place.location.cross_street))
         list.add(ModelGeneric("Localidad",place.location.locality))
         list.add(ModelGeneric("region",place.location.region))
        return list
    }
}