package com.challenge.androidchallenge.repository

import android.content.Context
import com.challenge.androidchallenge.repository.exception.NetworkException
import org.koin.core.KoinComponent
import org.koin.core.inject

open class BaseNetwork : KoinComponent {
    private val context: Context by inject()
    suspend fun <T> executeWithConnection(block: suspend () -> T): T {
        if (!context.isConnected() || context.isAirplaneModeActive()) {
            throw NetworkException()
        }
        return block()
    }

}