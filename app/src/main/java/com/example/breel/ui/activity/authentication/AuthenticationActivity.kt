package com.example.breel.ui.activity.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.databinding.ActivityAuthenticationBinding
import com.example.breel.databinding.FragmentLoginBinding
import com.example.breel.ui.fragment.authentication.LoginFragment
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthenticationBinding
    private val viewModel: AuthenticationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /*
    todo
    start destination ubah jadi onboarding
     */
}