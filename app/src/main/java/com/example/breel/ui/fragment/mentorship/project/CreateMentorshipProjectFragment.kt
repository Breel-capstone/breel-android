package com.example.breel.ui.fragment.mentorship.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.breel.databinding.FragmentCreateMentorshipProjectBinding
import com.example.breel.ui.component.MainActionBar
import com.google.android.material.textfield.MaterialAutoCompleteTextView

class CreateMentorshipProjectFragment : Fragment() {

    private lateinit var binding: FragmentCreateMentorshipProjectBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentCreateMentorshipProjectBinding.inflate(inflater, container, false)
        setUpActionBar()
        setUpDropdownItem()
        return binding.root
    }

    private fun setUpDropdownItem() {
        val dropdownLayout = binding.dropdownLayout
        val items = getDummyDropdownItems()
        (dropdownLayout.editText as? MaterialAutoCompleteTextView)?.setSimpleItems(items)
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Mentorsip Proyek")
        mainActionBar.setBackButton()
    }

    fun getDummyDropdownItems(): Array<String> {
        val lst = arrayOf(
            "Sistem Prediksi Tindakan Pelanggan - PT ABC",
            "Sistem Informasi Konten Sosial Media - PT XYZ"
        )
        return lst
    }
}