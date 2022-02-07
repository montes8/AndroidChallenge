package com.challenge.androidchallenge.repository.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import com.challenge.androidchallenge.R
import com.challenge.androidchallenge.repository.exception.GenericException
import com.challenge.androidchallenge.repository.exception.NetworkException
import com.challenge.androidchallenge.repository.utils.ErrorMessage
import com.challenge.androidchallenge.repository.utils.ErrorMessageDefault
import com.challenge.androidchallenge.repository.utils.generalErrorMessage
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import okhttp3.ResponseBody
import retrofit2.Response


fun Context?.isConnected(): Boolean {
    return this?.let {
        val cm = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.getNetworkCapabilities(cm.activeNetwork)
            ?.hasCapability((NetworkCapabilities.NET_CAPABILITY_INTERNET)) ?: false
    } ?: false
}

fun Context?.isAirplaneModeActive(): Boolean {
    return this?.let {
        return Settings.Global.getInt(it.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0
    } ?: false
}

fun <T> Response<T>.validateBody() : T {
    this.body()?.let {
        return it
    } ?: throw NullPointerException()
}

fun ResponseBody?.toCompleteErrorModel() : GenericException? {
    return this?.let {
        return Gson().fromJson(it.string(), GenericException::class.java)
    } ?: GenericException(generalErrorMessage)
}

fun Activity.hideKeyboard() {
    val inputMethodManager: InputMethodManager =
        this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}

fun mapperError(error : Throwable):String{
    return when (error) {
        is NetworkException -> { ErrorMessage }
        is GenericException -> {error.messageDefault}
        else -> { ErrorMessageDefault }
    }
}

fun ImageView.loadImageUrlPicasso(url: String?) {
    url?.let {
        if (it.isNotEmpty()) {
            Picasso.get()
                .load(it).placeholder(R.drawable.shape_place_holder)
                .into(this)
        }
    }
}

