package com.challenge.androidchallenge.repository.exception

import com.challenge.androidchallenge.repository.utils.generalErrorMessage


class GenericException(val messageDefault: String) : Exception(){
    fun getException():Exception {
        return GenericException(generalErrorMessage)
    }
}

class NetworkException() : Exception()

