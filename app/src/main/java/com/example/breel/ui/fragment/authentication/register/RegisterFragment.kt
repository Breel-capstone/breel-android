package com.example.breel.ui.fragment.authentication.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.breel.data.Resource
import com.example.breel.databinding.FragmentRegisterBinding
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text?.trim().toString()
            val password = binding.edtPassword.text?.trim().toString()
            viewModel.register(email, password)
        }

        observe()
    }

    private fun observe() {
        viewModel.registerResultLiveData.observe(viewLifecycleOwner) {
            handleRegisterResult(it)
        }
    }

    private fun handleRegisterResult(status: Resource<AuthResult>) {
        Toast.makeText(requireContext(), status.toString(), Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val TAG = "Register Fragment"
    }
}
