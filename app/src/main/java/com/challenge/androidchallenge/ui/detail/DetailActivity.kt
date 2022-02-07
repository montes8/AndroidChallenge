package com.challenge.androidchallenge.ui.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.repository.utils.DATA_PLACE
import com.challenge.androidchallenge.ui.AppViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val viewModel: AppViewModel by viewModel(clazz = AppViewModel::class)
    private var dataModel: PlacesModel? = null
    private val adapterDetail = DetailAdapter()

    companion object {
        fun start(context: Context, dataPlace: PlacesModel) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DATA_PLACE, dataPlace)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        rvDetailPlace.adapter = adapterDetail
        intent.getSerializableExtra(DATA_PLACE).let {
            dataModel = it as PlacesModel?
        }
        configData()

    }

    private fun configData() {
        dataModel?.let {
            txtValueGeneric.text = it.name
            adapterDetail.parameterList = viewModel.configDataGeneric(it)
        }
    }
}