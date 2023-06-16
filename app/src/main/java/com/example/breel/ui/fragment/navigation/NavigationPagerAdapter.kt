package com.example.breel.ui.fragment.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.breel.ui.fragment.account.AccountFragment
import com.example.breel.ui.fragment.chat.list.ChatListFragment
import com.example.breel.ui.fragment.home.HomeFragment
import com.example.breel.ui.fragment.notification.NotificationFragment
import com.example.breel.ui.fragment.searching.SearchingFragment

class NavigationPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 5 // Number of fragments

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> ChatListFragment()
            2 -> SearchingFragment()
            3 -> NotificationFragment()
            4 -> AccountFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}