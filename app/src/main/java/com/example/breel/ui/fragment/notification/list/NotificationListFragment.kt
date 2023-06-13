package com.example.breel.ui.fragment.notification.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.databinding.FragmentNotificationListBinding
import com.example.breel.ui.fragment.notification.adapter.NotificationAdapter


class NotificationListFragment : Fragment() {

    private lateinit var binding: FragmentNotificationListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNotificationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = binding.rvPemberitahuan
        rv.layoutManager = LinearLayoutManager(requireActivity())

        var lst = listOf<NotificationData>(
            NotificationData("1","Title 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("2","Title 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("3","Title 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("4","Title 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("5","Title 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("6","Title 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("7","Title 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 9", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 10", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 11", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 12", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),
            NotificationData("8","Title 13", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam viverra, sapien vitae convallis venenatis, dui neque semper mauris, porttitor rutrum eros lorem in erat."),

            )

        val adapter = NotificationAdapter {

        }
        rv.adapter = adapter
        adapter.submitList(lst)
    }
}