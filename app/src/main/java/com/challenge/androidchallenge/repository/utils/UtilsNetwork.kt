package com.challenge.androidchallenge.repository

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import com.challenge.androidchallenge.repository.exception.GenericException
import com.challenge.androidchallenge.repository.exception.NetworkException
import com.challenge.androidchallenge.repository.utils.ErrorMessage
import com.challenge.androidchallenge.repository.utils.ErrorMessageDefault
import com.challenge.androidchallenge.repository.utils.generalErrorMessage
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response

fun getDensity(context: Context): Float {

    return context.resources.displayMetrics.density
}

fun getWidth(context: Context): Int {
    return context.resources.displayMetrics.widthPixels
}

fun getHeight(context: Context): Int {
    return context.resources.displayMetrics.heightPixels
}


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

