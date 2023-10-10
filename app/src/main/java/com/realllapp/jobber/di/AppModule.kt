package com.realllapp.jobber.di

import com.realllapp.jobber.dataPrefs.Prefs
import com.realllapp.jobber.fragments.home.HomeVM
import com.realllapp.jobber.fragments.start.birthdayFragment.BirthdayVM
import com.realllapp.jobber.fragments.start.finishFragment.FinishVM
import com.realllapp.jobber.fragments.start.genderFragment.GenderVM
import com.realllapp.jobber.fragments.start.startFragment.StartVM
import com.realllapp.jobber.retrofit.RetrofitInstance
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single { Prefs.getInstance(get()) }
    single { RetrofitInstance() }
    //start
    viewModelOf(::StartVM)
    viewModel{GenderVM()}
    viewModel{BirthdayVM()}
    viewModel{FinishVM(get(), get())}
    //main
    viewModel{ HomeVM(get()) }

}