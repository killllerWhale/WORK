package com.realllapp.jobber.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.realllapp.jobber.databinding.BottomSheetEnterRegistrationBinding

class RegistrationDialog(private val context: Context) {
    private val bottomSheetDialogEnterRegistrationBinding by lazy {
        BottomSheetEnterRegistrationBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    private val bottomSheetDialogEnterRegistration by lazy {
        BottomSheetDialog(context)
    }

    init {
        bottomSheetDialogEnterRegistration.setContentView(bottomSheetDialogEnterRegistrationBinding.root)
    }

    fun show() {
        bottomSheetDialogEnterRegistration.show()
    }

    fun dismiss() {
        bottomSheetDialogEnterRegistration.dismiss()
    }

    fun setOnRegistrationClickListener(listener: View.OnClickListener) {
        bottomSheetDialogEnterRegistrationBinding.btnRegistration.setOnClickListener(listener)
    }

    fun setOnEmailClickListener(listener: View.OnClickListener) {
        bottomSheetDialogEnterRegistrationBinding.btnEnterEmail.setOnClickListener(listener)
    }
}
