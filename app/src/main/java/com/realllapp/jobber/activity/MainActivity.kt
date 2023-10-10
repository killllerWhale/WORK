package com.realllapp.jobber.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.realllapp.jobber.databinding.ActivityMainBinding
import com.yandex.mapkit.MapKitFactory


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Yandex map
        MapKitFactory.setApiKey("681750a7-4171-4af9-849f-1e972badd38a")

        binding.bottomNavigationView.itemIconTintList = null
        val navController = binding.fragmentContainerViewMain.getFragment<NavHostFragment>().navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
