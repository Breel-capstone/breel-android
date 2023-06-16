package com.example.breel.ui.fragment.mentorship.daily

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breel.databinding.FragmentCreateDailyMentorshipBinding
import com.example.breel.ui.component.MainActionBar

class CreateDailyMentorshipFragment : Fragment() {

    private lateinit var binding: FragmentCreateDailyMentorshipBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentCreateDailyMentorshipBinding.inflate(inflater, container, false)
        setUpActionBar()
        return binding.root
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorhsip Harian")
        mainActionBar.setBackButton()
    }
}