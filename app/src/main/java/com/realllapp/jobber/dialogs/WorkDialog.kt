package com.realllapp.jobber.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.realllapp.jobber.databinding.BottomSheetEnterRegistrationBinding
import com.realllapp.jobber.databinding.ItemWorkBinding

class WorkDialog(private val context: Context) {
    private val bottomSheetDialogItemWorkBinding by lazy {
        ItemWorkBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    private val bottomSheetDialogWork by lazy {
        BottomSheetDialog(context)
    }

    init {
        bottomSheetDialogWork.setContentView(bottomSheetDialogItemWorkBinding.root)
    }

    fun show() {
        bottomSheetDialogWork.show()
    }

    fun dismiss() {
        bottomSheetDialogWork.dismiss()
    }
}
