package com.realllapp.jobber.fragments.start.genderFragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.FragmentGenderBinding
import com.realllapp.jobber.fragments.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenderFragment : ViewBindingFragment<FragmentGenderBinding>(), View.OnClickListener {
    private val vm : GenderVM by viewModel()
    private var gender = ""
    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentGenderBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnMale.setOnClickListener(this)
        binding.btnFemale.setOnClickListener(this)
        binding.bntNext.setOnClickListener(this)
        binding.btnGoBack.setOnClickListener{
            findNavController().popBackStack(R.id.genderFragment, true)
        }

    }

    @SuppressLint("ResourceAsColor")
    override fun onClick(view: View?) {
        binding.btnMale.setBackgroundColor(ContextCompat.getColor(binding.btnMale.context, R.color.gray))
        binding.btnFemale.setBackgroundColor(ContextCompat.getColor(binding.btnFemale.context, R.color.gray))
        when(view?.id){
            R.id.btnMale -> {
                binding.btnMale.setBackgroundColor(ContextCompat.getColor(binding.btnMale.context, R.color.teal))
                binding.bntNext.setBackgroundColor(ContextCompat.getColor(binding.bntNext.context, R.color.teal))
                gender = "Мужской"
            }
            R.id.btnFemale -> {
                binding.btnFemale.setBackgroundColor(ContextCompat.getColor(binding.btnFemale.context, R.color.teal))
                binding.bntNext.setBackgroundColor(ContextCompat.getColor(binding.bntNext.context, R.color.teal))
                gender = "Женский"
            }
            R.id.bntNext -> {
                if (gender != ""){
                    val bundle = bundleOf("gender" to gender)
                    findNavController().navigate(R.id.action_genderFragment_to_birthdayFragment, bundle)
                } else{
                    toast("Сделайте выбор")
                }
            }
        }
    }
    private fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}