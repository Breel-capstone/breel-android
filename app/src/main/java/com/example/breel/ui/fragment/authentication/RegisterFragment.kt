package com.example.breel.ui.fragment.authentication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.databinding.FragmentLoginBinding
import com.example.breel.databinding.FragmentRegisterBinding
import com.example.breel.ui.activity.authentication.AuthenticationViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GetTokenResult
import kotlinx.coroutines.tasks.await

class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: AuthenticationViewModel

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

        // todo: save credential di datastore

//        val token = status.data?.user?.getIdToken(false)
//        Log.i(TAG, "Token: ${token?.result?.token}")

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
        private const val TAG = "Register Fragment"
    }
}

/*
FirebaseUser mUser = FirebaseAuth.getInstance().getCurrentUser();
mUser.getIdToken(true)
    .addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
        public void onComplete(@NonNull Task<GetTokenResult> task) {
            if (task.isSuccessful()) {
                String idToken = task.getResult().getToken();
                // Send token to your backend via HTTPS
                // ...
            } else {
                // Handle error -> task.getException();
            }
        }
    });
 */