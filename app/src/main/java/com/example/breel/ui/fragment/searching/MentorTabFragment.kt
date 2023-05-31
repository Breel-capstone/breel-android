package com.example.breel.ui.fragment.searching

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breel.R
import com.example.breel.databinding.FragmentMentorTabBinding
import com.example.breel.ui.fragment.home.DummyMentor
import com.example.breel.ui.fragment.home.MentorAdapter

class MentorTabFragment : Fragment() {

    private lateinit var binding: FragmentMentorTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var lstMentor = listOf<DummyMentor>(
            DummyMentor("Lorem Ipsum", "1.000.000"),
            DummyMentor("Lorem Ipsum", "2.000.000"),
            DummyMentor("Lorem Ipsum", "3.000.000"),
            DummyMentor("Lorem Ipsum", "4.000.000"),
            )

        val rv = binding.rvMentor
        rv.layoutManager = GridLayoutManager(requireActivity(), 2)

        val adapter = MentorAdapter(lstMentor)
        rv.adapter = adapter
    }
}