package com.example.breel.ui.fragment.mentorship

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentDailyMentorshipBinding
import com.example.breel.databinding.FragmentMentorshipBinding
import com.example.breel.ui.component.MainActionBar

class DailyMentorshipFragment : Fragment() {

    private lateinit var binding: FragmentDailyMentorshipBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDailyMentorshipBinding.inflate(inflater, container, false)
        setUpActionBar()
        return binding.root
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorship Harianmu")
        mainActionBar.setBackButton()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val lst = listOf("Item 1", "Item 2", "Item 3")

        val rv = binding.rvYourMentee
        rv.layoutManager = LinearLayoutManager(requireActivity())

        val adapter = YourMenteeAdapter(lst)
        rv.adapter = adapter

        val rv2 = binding.rvPendingMentee
        rv2.layoutManager = LinearLayoutManager(requireActivity())

        val adapter2 = PendingMenteeAdapter(lst)
        rv2.adapter = adapter2
    }
}