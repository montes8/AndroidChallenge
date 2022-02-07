package com.challenge.androidchallenge.ui.home

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.repository.exception.GenericException
import com.challenge.androidchallenge.repository.exception.NetworkException
import com.challenge.androidchallenge.repository.hideKeyboard
import com.challenge.androidchallenge.repository.utils.EMPTY
import com.challenge.androidchallenge.repository.utils.ErrorMessage
import com.challenge.androidchallenge.repository.utils.ErrorMessageDefault
import com.challenge.androidchallenge.ui.AppViewModel
import com.challenge.androidchallenge.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModel(clazz = AppViewModel::class)

    private val adapterPlace = PlaceAdapter()
    private var textSearch = EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editSearch.apply {
            backListener = {
                onBackPressed()
            }
            searchTextChangeListener = {
                textSearch = it.toString()
                }
            searchListener = {
                openSearch(textSearch)
            }
        }
        editSearch.requestFocus()
        contentActivityList.setOnFocusChangeListener { _, hasFocus -> if (hasFocus) this.hideKeyboard() }


        rvPlace.adapter = adapterPlace

        viewModel.successPlacesLiveData.observe(this,{
            if (it.isNotEmpty()) {
                loadingEnd()
                adapterPlace.parameterList = it
                adapterPlace.onClickItem = {data -> DetailActivity.start(this, data) } }
        })


        viewModel.errorLiveData.observe(this,{
            mapperError(it)
        })
    }

    private fun openSearch(it : String){
        viewModel.getPlaces(it)
        loading()
    }

    private fun mapperError(error : Throwable){
        when (error) {
            is NetworkException -> {
                configError(ErrorMessage)
            }

            is GenericException -> {
                configError(error.messageDefault)
            }

            else -> {
                configError(ErrorMessageDefault)
            }
        }
    }

    private fun loading(){
        this.hideKeyboard()
        placeShimmer.visibility = View.VISIBLE
        contentErrorGeneric.visibility = View .GONE
        contentList.visibility = View .GONE
    }

    private fun loadingEnd(){
        contentList.visibility = View .VISIBLE
        contentErrorGeneric.visibility = View .GONE
        placeShimmer.visibility = View.GONE
    }

    private fun configError(message : String){
        contentErrorGeneric.visibility = View .VISIBLE
        placeShimmer.visibility = View.GONE
        contentList.visibility = View .GONE
        txtMessageError.text = message
    }
}