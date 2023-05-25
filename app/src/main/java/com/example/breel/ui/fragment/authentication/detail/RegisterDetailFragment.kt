package com.example.breel.ui.fragment.authentication.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breel.R
import com.example.breel.databinding.FragmentRegisterDetailBinding

class RegisterDetailFragment : Fragment() {
    private var _binding: FragmentRegisterDetailBinding? = null
    private val binding: FragmentRegisterDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}