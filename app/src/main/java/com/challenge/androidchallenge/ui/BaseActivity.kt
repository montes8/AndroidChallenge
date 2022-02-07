package com.challenge.androidchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun getLayout() : Int
    abstract fun setUpView()
    abstract fun observeViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        this.observeViewModel()
        this.setUpView()
    }
}