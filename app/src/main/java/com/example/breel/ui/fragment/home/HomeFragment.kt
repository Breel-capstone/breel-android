package com.example.breel.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.data.api.login.LoginResponse
import com.example.breel.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.login()
        viewModel.resultLiveData.observe(
            viewLifecycleOwner
        ) {
            handleLoginResult(it)
        }
    }

    private fun handleLoginResult(status: Resource<LoginResponse>) {
        when(status) {
            is Resource.Success -> {
                status.data?.let {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            is Resource.DataError -> {
                status.data?.let {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
            }
            else -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}