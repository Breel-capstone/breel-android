package com.example.breel.ui.activity.main

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
            it.show()
            it.title = title
        }
    }

    fun hideActionBar() {
        actionBar?.hide()
    }

    fun setBackButton() {
        actionBar?.let {
            it.setDisplayShowHomeEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }


}