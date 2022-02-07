package com.challenge.androidchallenge.ui.home

import android.view.View
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.repository.hideKeyboard
import com.challenge.androidchallenge.repository.mapperError
import com.challenge.androidchallenge.repository.utils.EMPTY
import com.challenge.androidchallenge.ui.AppViewModel
import com.challenge.androidchallenge.ui.BaseActivity
import com.challenge.androidchallenge.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: AppViewModel by viewModel(clazz = AppViewModel::class)

    private val adapterPlace = PlaceAdapter()
    private var textSearch = EMPTY

    override fun getLayout() = R.layout.activity_main

    override fun setUpView() {
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
    }

    override fun observeViewModel() {
        viewModel.successPlacesLiveData.observe(this,{
            if (it.isNotEmpty()) {
                loadingEnd()
                adapterPlace.parameterList = it
                adapterPlace.onClickItem = {data -> DetailActivity.start(this, data) } }
        })


        viewModel.errorLiveData.observe(this,{
            configError(mapperError(it))
        })
    }

    private fun openSearch(it : String){
        viewModel.getPlaces(it)
        loading()
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