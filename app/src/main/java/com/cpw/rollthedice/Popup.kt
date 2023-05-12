package com.cpw.rollthedice

import android.os.Bundle
import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.DialogFragment
import com.cpw.rollthedice.databinding.PopupBinding

class Popup(private val number: Int): DialogFragment() {

    private lateinit var popupBinding: PopupBinding

    var listener: Listener? = null

    interface Listener {
        fun onDialogButtonClicked()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val dialog = activity?.let {
           Dialog(it)
       }
        popupBinding = PopupBinding.inflate(layoutInflater)

        if (dialog != null) {
            popupBinding.numRolled.text = number.toString()
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(popupBinding.root)

            popupBinding.cool.setOnClickListener {
                dismiss()
            }
        }
        return dialog!!
    }
}