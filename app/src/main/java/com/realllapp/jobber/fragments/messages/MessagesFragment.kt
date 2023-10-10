package com.realllapp.jobber.fragments.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.realllapp.jobber.databinding.FragmentMessagesBinding
import com.realllapp.jobber.fragments.ViewBindingFragment

class MessagesFragment : ViewBindingFragment<FragmentMessagesBinding>() {

//    private val vm by lazy {
//        ViewModelProvider(this)[StartVM::class.java]
//    }

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMessagesBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //vm.createDb(requireContext())
    }
}