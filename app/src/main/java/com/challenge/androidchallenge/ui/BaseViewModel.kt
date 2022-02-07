package com.challenge.androidchallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    val errorLiveData = MutableLiveData<Throwable>()


    fun executeSuspendNotProgress(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            try {
                func()
            } catch (ex: Exception) {
                errorLiveData.postValue(ex)
            }
        }

}