package com.example.breel.ui.fragment.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.FragmentRekapSaranBinding


class RekapSaranFragment : Fragment() {

    private lateinit var binding: FragmentRekapSaranBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRekapSaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = binding.rvRekapSaran
        rv.layoutManager = LinearLayoutManager(requireActivity())

        var lst = listOf<DummyNotification>(
            DummyNotification("Title 11", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 13", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 14", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 15", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 16", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 17", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 18", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
        )

        val adapter = NotificationAdapter(lst)
        rv.adapter = adapter
    }
}