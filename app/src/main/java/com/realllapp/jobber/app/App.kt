package com.realllapp.jobber.app

import android.app.Application
import com.realllapp.jobber.di.appModule
import org.koin.core.context.startKoin
import androidx.appcompat.app.AppCompatDelegate
import com.realllapp.jobber.dataPrefs.IPrefs
import com.yandex.mapkit.MapKitFactory
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.get

class App : Application() {

    companion object {
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }

        val prefs: IPrefs = get(IPrefs::class.java)

        when (prefs.modeTheme) {
            1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
