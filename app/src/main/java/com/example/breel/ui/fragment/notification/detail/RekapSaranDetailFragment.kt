package com.example.breel.ui.fragment.notification.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breel.databinding.FragmentRekapSaranDetailBinding
import com.example.breel.ui.component.MainActionBar

class RekapSaranDetailFragment : Fragment() {

    private lateinit var binding: FragmentRekapSaranDetailBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRekapSaranDetailBinding.inflate(inflater, container, false)
        mainActionBar = MainActionBar(this)
        setUpActionBar()
        return binding.root
    }

    private fun setUpActionBar() {
        mainActionBar.setTitle("Rekap & Saran")
        mainActionBar.setBackButton()
    }
}