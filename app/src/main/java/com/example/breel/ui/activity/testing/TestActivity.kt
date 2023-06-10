package com.example.breel.ui.activity.testing

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.databinding.ActivityTestBinding
import com.example.breel.ui.fragment.mentorship.adapter.PendingMenteeAdapter
import com.example.breel.ui.fragment.mentorship.adapter.YourMenteeAdapter
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

        val lst = listOf("Item 1", "Item 2", "Item 3")

        val rv = binding.rvYourMentee
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = YourMenteeAdapter(lst)
        rv.adapter = adapter

        val rv2 = binding.rvPendingMentee
        rv2.layoutManager = LinearLayoutManager(this)

        val adapter2 = PendingMenteeAdapter(lst)
        rv2.adapter = adapter2
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val TAG = "TestActivity"
    }
}