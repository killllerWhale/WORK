package com.realllapp.jobber.fragments.start.startFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.FragmentStartBinding
import com.realllapp.jobber.dialogs.EntryDialog
import com.realllapp.jobber.dialogs.RegistrationDialog
import com.realllapp.jobber.fragments.ViewBindingFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StartFragment : ViewBindingFragment<FragmentStartBinding>() {

    private val registrationDialog: RegistrationDialog by lazy {
        RegistrationDialog(requireContext())
    }

    private val entryDialog: EntryDialog by lazy {
        EntryDialog(requireContext())
    }

    private val vm: StartVM by viewModel()

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentStartBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        entryDialog.setOnShowListener { dialog ->
            entryDialog.setBottomSheetStateExpanded(dialog)
        }

        binding.buttonEnter.setOnClickListener {
            registrationDialog.show()
        }

        binding.enterGuest.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_mainActivity)
        }

        registrationDialog.setOnEmailClickListener {
            registrationDialog.dismiss()
            entryDialog.show()
        }

        entryDialog.setOnEmailEnterClickListener {
            if (!vm.isNetworkAvailable(requireContext())) {
                toast("Отсутствует подключение к интернету")
            } else {
                lifecycleScope.launch {
                    vm.login(entryDialog.getEmailText(), entryDialog.getPasswordText())

                    vm.resultCode.collectLatest { resultCode ->
                        vm.error.collectLatest { error ->
                            if (resultCode && error == "") {
                                entryDialog.dismiss()
                                findNavController().navigate(R.id.action_startFragment_to_mainActivity)
                            } else if (error == "Неверный пароль") {
                                toast(error)
                            } else if (error == "Пользователь с таким email не зарегистрирован") {
                                toast(error)
                            } else {
                                toast("Сейчас на сервере неполадки, попробуйте позже")
                            }
                        }
                    }
                }
            }
        }

        registrationDialog.setOnRegistrationClickListener {
            registrationDialog.dismiss()
            findNavController().navigate(R.id.action_startFragment_to_genderFragment)
        }

    }
    private fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}
