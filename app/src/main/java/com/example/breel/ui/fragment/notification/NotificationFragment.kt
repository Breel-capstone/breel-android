package com.example.breel.ui.fragment.notification

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.breel.R
import com.example.breel.databinding.FragmentNotificationBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class NotificationFragment : Fragment() {

    private lateinit var binding: FragmentNotificationBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf (
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val TAG = "NotificationFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i(TAG, "masuk on view created")
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = NotificationPagerAdapter(requireActivity())
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        Log.i(TAG, "setelah mediator")
    }
}