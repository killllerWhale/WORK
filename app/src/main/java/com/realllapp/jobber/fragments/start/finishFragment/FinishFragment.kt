package com.realllapp.jobber.fragments.start.finishFragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.realllapp.jobber.R
import com.realllapp.jobber.databinding.DialogErrorBinding
import com.realllapp.jobber.databinding.FragmentFinishBinding
import com.realllapp.jobber.fragments.ViewBindingFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishFragment : ViewBindingFragment<FragmentFinishBinding>() {
    private val vm: FinishVM by viewModel()
    private lateinit var bindingDialog: DialogErrorBinding
    private lateinit var dialog: Dialog

    override fun makeBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentFinishBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindingDialog = DialogErrorBinding.inflate(layoutInflater)

        dialog = Dialog(requireContext()).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(bindingDialog.root)
        }

        binding.tvCheckProblemEmail.isEnabled = false
        binding.tvCheckProblemPassword.isEnabled = false

        binding.apply {
            btnNext.setOnClickListener {
                if (etName.text.isNotEmpty() && etEmail.text.isNotEmpty() && etPassword.text.isNotEmpty() && etRepeatPassword.text.isNotEmpty() && (etPassword.text.toString() == etRepeatPassword.text.toString())) {
                    lifecycleScope.launch {
                        vm.captchaCheck(etEmail.text.toString(), etPassword.text.toString())
                        when (vm.flag.value) {
                            0 -> {
                                tvCheckProblemPassword.isEnabled = false
                                tvCheckProblemPassword.setBackgroundResource(R.color.screen)
                                creteMessage(0)
                            }

                            1 -> {
                                tvCheckProblemEmail.isEnabled = false
                                tvCheckProblemEmail.setBackgroundResource(R.color.screen)
                                creteMessage(1)
                            }

                            2 -> {
                                tvCheckProblemEmail.isEnabled = false
                                tvCheckProblemEmail.setBackgroundResource(R.color.screen)
                                tvCheckProblemPassword.isEnabled = false
                                tvCheckProblemPassword.setBackgroundResource(R.color.screen)
                                vm.registrationUser(
                                    etName.text.toString(),
                                    etEmail.text.toString(),
                                    etPassword.text.toString(),
                                    arguments?.getString("birthday").toString(),
                                    arguments?.getString("gender").toString()
                                )
                                if (vm.response.value.status == "ok") {
                                    if (vm.response.value.result_code == 1) {
                                        findNavController().navigate(R.id.action_finishFragment_to_mainActivity)
                                        toast("Регистрация выполнена")
                                    } else {
                                        toast("Пользователь с таким email существует")
                                    }
                                } else {
                                    toast("Сейчас на сервере неполадки, попробуйте позже")
                                }
                            }
                        }
                    }
                } else {
                    if (etName.text.isEmpty() || etEmail.text.isEmpty() || etPassword.text.isEmpty() || etRepeatPassword.text.isEmpty()) {
                        toast("Введите данные")
                    } else {
                        toast("Пароли не совпадают")
                    }
                }
            }
        }

        binding.tvCheckProblemEmail.setOnClickListener {
            dialog.show()
        }

        binding.tvCheckProblemPassword.setOnClickListener {
            dialog.show()
        }

        bindingDialog.btnFix.setOnClickListener {
            dialog.dismiss()
        }

        binding.btnGoBack.setOnClickListener {
            findNavController().popBackStack(R.id.finishFragment, true)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun creteMessage(flag: Int) {
        if (flag == 0) {
            binding.tvCheckProblemEmail.isEnabled = true
            binding.tvCheckProblemEmail.setBackgroundResource(R.drawable.error_registration)
            bindingDialog.tvError.text =
                "Для ввода корректного email необходимо следовать следующим правилам:\n" +
                        "\n" +
                        "1. email должен содержать символ \"@\";\n" +
                        "2. email должен содержать доменную зону, состоящую не менее, чем из двух символов, например \".com\", \".org\", \".edu\" и т.д.;\n" +
                        "3. email не должен содержать пробелов, специальных символов (кроме символа \"@\") или русских букв;\n" +
                        "4. email должен начинаться с буквы или цифры."
            toast("Некорректный email")
        } else {
            binding.tvCheckProblemPassword.isEnabled = true
            binding.tvCheckProblemPassword.setBackgroundResource(R.drawable.error_registration)
            bindingDialog.tvError.text =
                "При создании пароля необходимо учитывать следующие условия:\n" +
                        "\n" +
                        "1. Длина пароля должна быть не менее 8 символов\n" +
                        "2. Пароль должен содержать хотя бы одну цифру\n" +
                        "3. Пароль должен содержать хотя бы одну прописную букву\n" +
                        "4. Пароль должен содержать хотя бы одну заглавную букву\n" +
                        "5. Пароль должен содержать хотя бы один из символов @#\$%^&+=\n" +
                        "6. Пароль не должен содержать пробелов\n" +
                        "7. Вы можете использовать комбинацию цифр, букв и символов, чтобы создать надежный пароль, который будет защищать ваши личные данные. Пожалуйста, убедитесь, что ваш пароль соответствует этим условиям."
        }
        toast("Некорректный пароль")
    }

    private fun Fragment.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(activity, message, duration).show()
}