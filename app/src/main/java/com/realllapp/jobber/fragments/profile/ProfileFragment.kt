package com.realllapp.jobber.fragments.profile

import DoneFragment
import ReviewsFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.FragmentProfileBinding
import com.realllapp.jobber.fragments.ViewBindingFragment

class ProfileFragment : ViewBindingFragment<FragmentProfileBinding>() {

//    private val vm by lazy {
//        ViewModelProvider(this)[StartVM::class.java]
//    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProfileBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //vm.createDb(requireContext())
        val nestedNavHost = childFragmentManager.findFragmentById(R.id.fragmentContainerViewProfile) as NavHostFragment
        val navController = nestedNavHost.navController

        binding.llDone.setOnClickListener {
            val currentDestination = navController.currentDestination
            if (currentDestination?.id != R.id.doneFragment) {
                navController.navigate(R.id.action_reviewsFragment_to_doneFragment)
            }
        }

        binding.llReviews.setOnClickListener {
            val currentDestination = navController.currentDestination
            if (currentDestination?.id != R.id.reviewsFragment) {
                navController.navigate(R.id.action_doneFragment_to_reviewsFragment)
            }
        }
    }
}