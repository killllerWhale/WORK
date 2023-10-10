package com.realllapp.jobber.fragments.start.startFragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.ViewModel
import com.realllapp.jobber.dataPrefs.IPrefs
import com.realllapp.jobber.retrofit.RetrofitInstance
import com.realllapp.jobber.retrofit.dataclasses.User
import kotlinx.coroutines.flow.MutableStateFlow

class StartVM(
    private val prefs: IPrefs,
    private val retrofitInstance: RetrofitInstance
) : ViewModel() {
    var user = MutableStateFlow(User("", "", "", "", "", "", "", -1, null,null, "", null))
    var resultCode = MutableStateFlow(false)
    var error = MutableStateFlow("")

    suspend fun login(
        userEmail: String,
        userPassword: String
    ) {
        user.value = retrofitInstance.api.performUserLogin(userEmail, userPassword)
        if (user.value.result_code == 1) {
            resultCode.value = true
            with(prefs) {
                statusUser = 1
                nameUser = user.value.name
            }
        }
        if (user.value.error != null) {
            error.value = user.value.error!!
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
    }
}