package com.example.breel.ui.fragment.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.FragmentPemberitahuanBinding


class PemberitahuanFragment : Fragment() {

    private lateinit var binding: FragmentPemberitahuanBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPemberitahuanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = binding.rvPemberitahuan
        rv.layoutManager = LinearLayoutManager(requireActivity())

        var lst = listOf<DummyNotification>(
            DummyNotification("Title 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            DummyNotification("Title 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            )

        val adapter = NotificationAdapter(lst)
        rv.adapter = adapter
    }
}