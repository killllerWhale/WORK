package com.realllapp.jobber.dataPrefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Prefs(context: Context) : IPrefs {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("JOBBER_APP", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_MODE_THEME = "modeTheme"
        private const val KEY_STATUS_USER = "statusUser"
        private const val KEY_NAME_USER = "nameUser"

        fun getInstance(context: Context): IPrefs {
            return Prefs(context)
        }
    }

    override var modeTheme: Int?
        get() = sharedPreferences.getInt(KEY_MODE_THEME, 1)
        set(value) = sharedPreferences.edit { putInt(KEY_MODE_THEME, value!!) }

    override var statusUser: Int?
        get() = sharedPreferences.getInt(KEY_STATUS_USER, 1)
        set(value) = sharedPreferences.edit { putInt(KEY_STATUS_USER, value!!) }

    override var nameUser: String?
        get() = sharedPreferences.getString(KEY_NAME_USER, "Гость")
        set(value) = sharedPreferences.edit { putString(KEY_NAME_USER, value!!) }


    // Метод для удаления всех преференсов
    override fun clearPrefs() {
        sharedPreferences.edit().clear().apply()
    }
}

