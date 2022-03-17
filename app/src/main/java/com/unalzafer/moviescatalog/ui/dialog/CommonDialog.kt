package com.unalzafer.moviescatalog.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.unalzafer.moviescatalog.R

class CommonDialog(message: String?) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(
                requireActivity().layoutInflater.inflate(
                    R.layout.dialog_general,
                    null
                )
            )

            alertDialog.create()
        } ?: throw IllegalStateException("Activity is null !!")
    }
}