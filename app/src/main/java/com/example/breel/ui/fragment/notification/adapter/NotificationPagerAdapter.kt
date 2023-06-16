package com.example.breel.ui.fragment.notification.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.breel.ui.fragment.notification.feedback.FeedbackFragment
import com.example.breel.ui.fragment.notification.list.NotificationListFragment

class NotificationPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = NotificationListFragment()
            1 -> fragment = FeedbackFragment()
        }
        return fragment as Fragment
    }

}