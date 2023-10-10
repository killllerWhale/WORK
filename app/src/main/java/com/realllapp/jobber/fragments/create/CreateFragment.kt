package com.realllapp.jobber.fragments.create

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.FragmentCreateBinding
import com.realllapp.jobber.databinding.FragmentHomeBinding
import com.realllapp.jobber.fragments.ViewBindingFragment


class CreateFragment : ViewBindingFragment<FragmentCreateBinding>() {

//    private val vm by lazy {
//        ViewModelProvider(this)[StartVM::class.java]
//    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentCreateBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        binding.srType.setAdapter(
//            ArrayAdapter(
//                requireContext(),
//                resources.getStringArray(R.array.type)
//            )
//        )
        //vm.createDb(requireContext())
    }
}