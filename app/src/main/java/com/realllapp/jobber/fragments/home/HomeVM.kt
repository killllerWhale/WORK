package com.realllapp.jobber.fragments.home

import androidx.lifecycle.ViewModel
import com.realllapp.jobber.dataPrefs.IPrefs
import kotlinx.coroutines.flow.MutableStateFlow


class HomeVM(
    private val prefs: IPrefs
) : ViewModel() {

    val nameUser = MutableStateFlow("Гость")

    init {
        if (prefs.statusUser == 1) {
            nameUser.value = prefs.nameUser.toString()
        }
    }

}