package com.example.breel.ui.component

import android.view.View
import com.example.breel.R
import com.google.android.material.snackbar.Snackbar

@Suppress("DEPRECATION")
class StatusSnackBar(private val rootView: View) {
    fun showSuccess(
        message: String,
    ) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(rootView.context.resources.getColor(R.color.green_hookers_800))
            .setTextColor(rootView.context.resources.getColor(R.color.white))

        snackbar.setAction("Dismiss") {
            snackbar.dismiss()
        }.setActionTextColor(rootView.context.resources.getColor(R.color.white))

        snackbar.show()
    }

    fun showError(
        message: String,
    ) {
        val snackbar = Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(rootView.context.resources.getColor(R.color.errorColor))
            .setTextColor(rootView.context.resources.getColor(R.color.white))

        snackbar.setAction("Dismiss") {
            snackbar.dismiss()
        }.setActionTextColor(rootView.context.resources.getColor(R.color.white))

        snackbar.show()
    }

}