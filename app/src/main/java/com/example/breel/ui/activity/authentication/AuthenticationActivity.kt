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

        binding.btnTest.setOnClickListener {
            viewModel.register("adiva456@gmail.com", "456adiva")
        }

        observe()

        val mFragmentManager = supportFragmentManager
        val mLoginFragment = LoginFragment()
        val fragment = mFragmentManager.findFragmentByTag(LoginFragment::class.java.simpleName)
    }

    private fun observe() {
        viewModel.registerResultLiveData.observe(this) {
            handleRegisterResult(it)
        }
    }

    private fun handleRegisterResult(status: Resource<AuthResult>) {
        Toast.makeText(this, status.toString(), Toast.LENGTH_LONG).show()
    }


}