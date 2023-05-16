package com.example.breel.ui.activity.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.breel.databinding.ActivityMainBinding
import com.example.breel.ui.activity.authentication.AuthenticationActivity
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btnLogout.setOnClickListener {
//            firebaseAuth.signOut()
//            finish()
//        }

        val user = firebaseAuth.currentUser

//        if (user == null) {
//            val intent = Intent(this@MainActivity, AuthenticationActivity::class.java)
//            startActivity(intent)
//
//        }

        /*
        todo
        tampilin splash screen.
        cek user sudah login atau belum.
        jika user sudah logged in, navigate ke home.
        jika belum, navigate ke authentication.
         */

    }
}