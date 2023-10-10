package com.realllapp.jobber.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.realllapp.jobber.databinding.BottomSheetEnterEmailBinding

class EntryDialog(private val context: Context) {
    private val bottomSheetDialogEnterEmailBinding by lazy {
        BottomSheetEnterEmailBinding.inflate(
            LayoutInflater.from(context)
        )
    }

    private val bottomSheetDialogEnterEmail by lazy {
        BottomSheetDialog(context)
    }

    init {
        bottomSheetDialogEnterEmail.setContentView(bottomSheetDialogEnterEmailBinding.root)
    }

    fun show() {
        bottomSheetDialogEnterEmail.show()
    }

    fun dismiss() {
        bottomSheetDialogEnterEmail.dismiss()
    }

    fun getEmailText(): String {
        return bottomSheetDialogEnterEmailBinding.etEmail.text.toString()
    }

    fun getPasswordText(): String {
        return bottomSheetDialogEnterEmailBinding.etPassword.text.toString()
    }

    fun setOnEmailEnterClickListener(listener: View.OnClickListener) {
        bottomSheetDialogEnterEmailBinding.btnEnter.setOnClickListener(listener)
    }

    fun setOnShowListener(listener: (dialog: BottomSheetDialog) -> Unit) {
        bottomSheetDialogEnterEmail.setOnShowListener { dialog ->
            listener(dialog as BottomSheetDialog)
        }
    }

    fun setBottomSheetStateExpanded(dialog: BottomSheetDialog) {
        val bottomSheetInternal = dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as View
        BottomSheetBehavior.from(bottomSheetInternal).run {
            state = BottomSheetBehavior.STATE_EXPANDED
        }
    }
}
