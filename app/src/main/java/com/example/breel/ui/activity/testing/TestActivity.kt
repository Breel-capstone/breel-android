package com.example.breel.ui.activity.testing

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.ActivityTestBinding
import com.example.breel.ui.fragment.chat.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()

    private var _binding: ActivityTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.test()
        _binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()

        val lst = listOf(
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen),
            getString(R.string.lorem_2sen)
        )

        val rv: RecyclerView = binding.recyclerView
        rv.layoutManager = LinearLayoutManager(this)

        val adapter = MessageAdapter(lst)
        rv.adapter = adapter
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}