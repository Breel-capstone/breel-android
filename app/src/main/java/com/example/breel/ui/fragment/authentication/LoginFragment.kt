package com.example.breel.ui.fragment.authentication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.databinding.FragmentLoginBinding
import com.example.breel.ui.activity.authentication.AuthenticationViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: AuthenticationViewModel
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProvider(this).get(AuthenticationViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text.trim().toString()
            val password = binding.edtPassword.text.trim().toString()
            viewModel.login(email, password)
        }

        binding.tvToRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        observe()

        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.btnSigninGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)

    }

    private var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
//        if (result.resultCode == Activity.RESULT_OK) {
//            Log.i(TAG,"Result OK")
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account: GoogleSignInAccount = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
//        }
//        Log.i(TAG, "test")
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential: AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
        Log.i(TAG, credential.toString())
        viewModel.signInWithGoogle(credential)
    }

    private fun observe() {
        viewModel.loginResultLiveData.observe(viewLifecycleOwner) {
            handleLoginResult(it)
        }
        viewModel.signInGoogleResultLiveData.observe(viewLifecycleOwner) {
            handleSignInResult(it)
        }
    }

    private fun handleSignInResult(status: Resource<AuthResult>) {
        Toast.makeText(requireContext(), status.toString(), Toast.LENGTH_LONG).show()

        Log.i(TAG, "sign in result function")

        val mUser = status.data?.user?.getIdToken(true)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val token = it.result.token
                    Toast.makeText(requireContext(), "Token", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "Token: $token")
                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun handleLoginResult(status: Resource<AuthResult>) {
        Toast.makeText(requireContext(), status.toString(), Toast.LENGTH_LONG).show()

        // todo: save credential di datastore

//        val token = status.data?.user?.getIdToken(false)
//        Log.i(TAG, "Token: ${token?.result?.token.toString()}")

        val mUser = status.data?.user?.getIdToken(true)
            ?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val token = it.result.token
                    Toast.makeText(requireContext(), "Token", Toast.LENGTH_SHORT).show()
                    Log.i(TAG, "Token: $token")
                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
                }
            }
    }



    companion object {
        private const val TAG = "Login Fragment"
    }
}