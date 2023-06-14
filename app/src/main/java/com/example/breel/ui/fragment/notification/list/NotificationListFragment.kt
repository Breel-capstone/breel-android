package com.example.breel.ui.fragment.notification.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.model.notification.NotificationData
import com.example.breel.databinding.FragmentNotificationListBinding
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
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
            NotificationData(
                "1",
                "Selamat! Proposal Anda telah lolos!",
                "PT. Blablabla sudah mereview proposal project Anda dan tertarik untuk bekerja sama dengan Anda!"
            ),
            NotificationData(
                "2",
                "Michael Kusumawijaya menerima Anda sebagai mentee",
                "Michael Kusumawijaya menerima proposal Anda sebagai mentee untuk program Mentor On A Project."
            ),
            NotificationData(
                "3",
                "Mohon Maaf! Proposal Anda telah ditolak!",
                "PT. Blablabla sudah mereview proposal project Anda dan tertarik untuk bekerja sama dengan Anda!"
            ),
            NotificationData(
                "4",
                "Agnes Monika melihat profil Anda",
                "Agnes Monika telah melihat profil Anda. Agnes Monika merupakan Design Editor pada majalah Harper Bazaar’s."
            ),
        )

        lst.forEach { }
        val adapter = NotificationAdapter {
            val destination =
                NavigationFragmentDirections.actionNavigationFragment2ToProposalAcceptedFragment()
            findNavController().navigate(destination)
        }
        rv.adapter = adapter
        adapter.submitList(lst)
    }
}