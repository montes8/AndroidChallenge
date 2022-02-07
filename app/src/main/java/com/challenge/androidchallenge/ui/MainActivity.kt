package com.challenge.androidchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.androidchallenge.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModel(clazz = AppViewModel::class)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       // viewModel.getPlaces("caffe")
    }
}