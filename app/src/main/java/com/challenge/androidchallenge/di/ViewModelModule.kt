package com.challenge.androidchallenge.di

import com.challenge.androidchallenge.ui.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { AppViewModel() }
}
