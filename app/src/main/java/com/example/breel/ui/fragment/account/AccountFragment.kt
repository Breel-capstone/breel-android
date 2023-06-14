package com.example.breel.ui.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.breel.R
import com.example.breel.databinding.FragmentAccountBinding
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections

class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClickListener()
    }

    private fun setOnClickListener() {
        binding.btnMyProfile.setOnClickListener {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToProfileOpenedFragment()
            findNavController().navigate(destination)
        }
        binding.btnMentoring.setOnClickListener {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToMentorshipFragment()
            findNavController().navigate(destination)
        }
        binding.btnMyWallet.setOnClickListener {
            Toast.makeText(requireContext(), "Dompet Saya clicked!", Toast.LENGTH_SHORT).show()
        }
        binding.btnSetting.setOnClickListener {
            Toast.makeText(requireContext(), "Pengaturan clicked!", Toast.LENGTH_SHORT).show()
        }
        binding.btnExit.setOnClickListener {
            Toast.makeText(requireContext(), "Keluar clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}