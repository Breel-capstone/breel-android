package com.example.breel.ui.fragment.searching

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.breel.ui.fragment.searching.clientproject.ProjectTabFragment
import com.example.breel.ui.fragment.searching.mentor.MentorTabFragment
import com.example.breel.ui.fragment.searching.mentorproject.ProjectMentorTabFragment

class SearchingSectionsPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ProjectTabFragment()
            1 -> fragment = ProjectMentorTabFragment()
            2 -> fragment = MentorTabFragment()
        }
        return fragment as Fragment
    }
}