package com.example.breel.ui.fragment.authentication.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.breel.AuthGroupDirections
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.databinding.FragmentLoginBinding
import com.example.breel.ui.component.StatusSnackBar
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var statusSnackBar: StatusSnackBar

    @Inject
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel.checkProfileDetail()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusSnackBar = StatusSnackBar(view)
        setUpAction()
        observeViewModel()
        setGoogleSignInClient()
    }


    private fun setUpAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmail.text?.trim().toString()
            val password = binding.edtPassword.text?.trim().toString()
            viewModel.login(email, password)
        }

        binding.tvToRegister.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment2)
        }
        binding.btnSigninGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun setGoogleSignInClient() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
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

    private fun observeViewModel() {
        viewModel.loginResultLiveData.observe(viewLifecycleOwner) {
            handleSignInResult(it)
        }
        viewModel.signInGoogleResultLiveData.observe(viewLifecycleOwner) {
            handleSignInResult(it)
        }
        viewModel.checkProfileResult.observe(viewLifecycleOwner) {
            handleCheckProfileResult(it)
        }
    }

    private fun handleCheckProfileResult(status: Resource<Boolean>) {
        binding.progressCircular.visibility = View.GONE

        when (status) {
            is Resource.Success -> status.data?.let {
                if (it) {
                    findNavController().navigate(AuthGroupDirections.actionGlobalHomeNavigation())
                    return
                }
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterDetailFragment2())

            }

            is Resource.DataError -> {
                binding.progressCircular.visibility = View.GONE
            }

            else -> {
                binding.progressCircular.visibility = View.VISIBLE
            }
        }
    }

    private fun handleSignInResult(status: Resource<AuthResult>) {
        when (status) {
            is Resource.Success -> status.data?.let {
                binding.progressCircular.visibility = View.GONE
                if (it.user == null) {
                    statusSnackBar.showError("Akun anda belum terregister")
                    return
                }
                viewModel.checkProfileDetail()
            }

            is Resource.DataError -> {
                binding.progressCircular.visibility = View.GONE
                statusSnackBar.showError("Email atau password anda salah")
            }

            else -> {
                binding.progressCircular.visibility = View.VISIBLE
            }
        }

    }

    companion object {
        private const val TAG = "Login Fragment"
    }
}