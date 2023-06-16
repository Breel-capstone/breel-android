package com.example.breel.ui.component

import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

@Suppress("DEPRECATION")
class MainActionBar(fragment: Fragment) {

    private var actionBar: ActionBar? = null

    init {
        fragment.setHasOptionsMenu(true)
        val appCompatActivity = fragment.requireActivity() as AppCompatActivity
        actionBar = appCompatActivity.supportActionBar
        actionBar?.hide()
    }

    fun setTitle(title: String) {
        actionBar?.let {
            Log.d("MainActionBar", "setTitle: $title")
            it.hide()
            it.show()
            it.title = title
        }
    }

    fun hideActionBar() {
        actionBar?.let {
            it.setDisplayShowHomeEnabled(false)
            it.setDisplayHomeAsUpEnabled(false)
            it.hide()
        }
    }

    fun setBackButton() {
        actionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


}