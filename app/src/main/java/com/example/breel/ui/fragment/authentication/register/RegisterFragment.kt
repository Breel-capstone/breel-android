package com.example.breel.ui.fragment.authentication.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.breel.data.Resource
import com.example.breel.databinding.FragmentRegisterBinding
import com.example.breel.ui.component.StatusSnackBar
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var statusSnackBar: StatusSnackBar

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
        statusSnackBar = StatusSnackBar(view)
        setListener()
        observe()
    }

    private fun setListener() {
        binding.btnRegister.setOnClickListener {
            val email = binding.edtEmail.text?.trim().toString()
            val password = binding.edtPassword.text?.trim().toString()
            viewModel.register(email, password)
        }
        binding.toLogin.setOnClickListener {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragment2ToLoginFragment())
        }
    }

    private fun observe() {
        viewModel.registerResultLiveData.observe(viewLifecycleOwner) {
            handleRegisterResult(it)
        }
    }

    private fun handleRegisterResult(status: Resource<AuthResult>) {
        when (status) {
            is Resource.Success -> status.data?.let {
                binding.progressbar.visibility = View.GONE
                statusSnackBar.showSuccess("Kamu terregistrasi!")
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragment2ToRegisterDetailFragment2())
            }

            is Resource.DataError -> {
                binding.progressbar.visibility = View.GONE
                status.errorMessage?.let { statusSnackBar.showError(it) }
            }

            else -> {
                binding.progressbar.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        private const val TAG = "Register Fragment"
    }
}
