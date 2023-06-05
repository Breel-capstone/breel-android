package com.example.breel.ui.activity.testing

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.breel.databinding.ActivityTestBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()

    private var _binding: ActivityTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.test {
            Log.d(TAG, "onCreate: $it")
        }
        _binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "TestActivity"
    }
}