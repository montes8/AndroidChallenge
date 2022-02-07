package com.challenge.androidchallenge.ui.detail

import android.content.Context
import android.content.Intent
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.entity.PlacesModel
import com.challenge.androidchallenge.repository.utils.DATA_PLACE
import com.challenge.androidchallenge.repository.utils.loadImageUrlPicasso
import com.challenge.androidchallenge.ui.AppViewModel
import com.challenge.androidchallenge.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : BaseActivity() {

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

    override fun getLayout() = R.layout.activity_detail

    override fun setUpView() {
        rvDetailPlace.adapter = adapterDetail
        intent.getSerializableExtra(DATA_PLACE).let {
            dataModel = it as PlacesModel?
            dataModel?.let {data ->
                txtValueGeneric.text = data.name
                adapterDetail.parameterList = viewModel.configDataGeneric(data)
                if (data.categories.isNotEmpty())imageGenericDetail.loadImageUrlPicasso("${data.categories[0].icon.prefix}${data.categories[0].icon.suffix}}")
            }
        }
    }

    override fun observeViewModel() {
        //none
    }
}