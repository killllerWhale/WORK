package com.realllapp.jobber.fragments.start.finishFragment

import androidx.lifecycle.ViewModel
import com.realllapp.jobber.dataPrefs.IPrefs
import com.realllapp.jobber.retrofit.RetrofitInstance
import com.realllapp.jobber.retrofit.dataclasses.CreateUserResponse
import kotlinx.coroutines.flow.MutableStateFlow

class FinishVM(
    private val prefs: IPrefs,
    private val retrofitInstance: RetrofitInstance
) : ViewModel() {
    var flag = MutableStateFlow(2)
    var response = MutableStateFlow(CreateUserResponse(-1, "default"))

    fun captchaCheck(userEmail: String, userPassword: String) {
        val emailPattern =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$".toRegex()
        val passwordPattern =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$".toRegex()

        if (emailPattern.matches(userEmail)) {
            if (passwordPattern.matches(userPassword)) {
                flag.value = 2
            } else {
                flag.value = 1
            }
        } else {
            flag.value = 0

        }
    }

    //id name email password birthday gender data_reg
    suspend fun registrationUser(
        userName: String,
        userEmail: String,
        userPassword: String,
        userBirthday: String,
        userGender: String
    ) {
        response.value = retrofitInstance.api.performUserRegistration(
            userName,
            userEmail,
            userPassword,
            userBirthday,
            userGender
        )
        if (response.value.status == "ok") {
            if (response.value.result_code == 1) {
                with(prefs) {
                    statusUser = 1
                    nameUser = userName
                }
            }
        }
    }
}