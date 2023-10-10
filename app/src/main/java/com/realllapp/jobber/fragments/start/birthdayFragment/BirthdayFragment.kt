package com.realllapp.jobber.fragments.start.birthdayFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.FragmentBirthdayBinding
import com.realllapp.jobber.fragments.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class BirthdayFragment : ViewBindingFragment<FragmentBirthdayBinding>() {
    private val vm : BirthdayVM by viewModel()

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentBirthdayBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.btnGoBack.setOnClickListener{
            findNavController().popBackStack(R.id.birthdayFragment, true)
        }

        binding.apply {
            btnNext.setOnClickListener {
                val day = etDay.text.toString()
                val month = etMonth.text.toString()
                val year = etYear.text.toString()
                if (day.isNotEmpty() && month.isNotEmpty() && year.isNotEmpty()) {
                    val birthday = "$year-$month-$day"
                    val bundle = bundleOf("gender" to arguments?.getString("gender"), "birthday" to birthday)
                    findNavController().navigate(R.id.action_birthdayFragment_to_finishFragment, bundle)
                } else {
                    toast("Заполните поля")
                }
            }
        }

    }
    private fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}