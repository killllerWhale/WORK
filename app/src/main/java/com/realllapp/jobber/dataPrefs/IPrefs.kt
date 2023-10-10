package com.realllapp.jobber.dataPrefs

interface IPrefs {
    var modeTheme: Int?
    var statusUser: Int?
    var nameUser: String?
    fun clearPrefs()
}