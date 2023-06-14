package com.example.breel.ui.fragment.notification.feedback

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.databinding.FragmentRekapSaranBinding
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
import com.example.breel.ui.fragment.notification.adapter.NotificationAdapter


class FeedbackFragment : Fragment() {

    private lateinit var binding: FragmentRekapSaranBinding

    companion object {
        const val TAG = "FeedbackFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRekapSaranBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "view : $view")
        super.onViewCreated(view, savedInstanceState)

        val rv: RecyclerView = binding.rvRekapSaran
        rv.layoutManager = LinearLayoutManager(requireActivity())

        var lst = listOf<NotificationData>(
            NotificationData(
                "1",
                "Berikut rekap kegiatanmu hari ini.",
                "Pada minggu ini, kamu sudah mengajukan Project Proposal kepada 3 clients. Mereka menyukai portofoliomu! Berikut beberapa improvements yang dapat kamu lakukan!"
            ),
            NotificationData(
                "2",
                "Berikut rekap kegiatanmu hari ini.",
                "Pada minggu ini, kamu sudah mengajukan Project Proposal kepada 2 clients, dan melakukan 1 wawancara."
            ),
            NotificationData(
                "3",
                "Berikut rekap kegiatanmu hari ini.",
                "Selamat! Pada minggu ini kamu berhasil mendapatkan 1 project dengan client Agnes Monika! Akan tetapi, masih ada beberapa improvement yang dapat kamu lakukan."
            ),
            NotificationData(
                "4",
                "Berikut rekap kegiatanmu hari ini.",
                "Pada minggu ini, kamu sudah melakukan 2 wawancara dengan client, Raihan Irawan dan Farhan Burhan."
            ),
        )

        val adapter = NotificationAdapter {
            Log.i(TAG, "$it")
            val destination =
                NavigationFragmentDirections.actionNavigationFragment2ToRekapSaranDetailFragment(it)
            findNavController().navigate(destination)
        }
        adapter.submitList(lst)
        rv.adapter = adapter
    }
}