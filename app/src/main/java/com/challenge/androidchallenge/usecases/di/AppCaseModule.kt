package com.challenge.androidchallenge

import com.challenge.androidchallenge.usecases.usecase.AppUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { AppUseCase() }
}