package com.example.breel.ui.fragment.searching

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.breel.R
import com.example.breel.databinding.FragmentSearchingBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.home.HomeViewModel
import com.example.breel.ui.fragment.home.mentor.MentorAdapter
import com.example.breel.ui.fragment.home.project.ProjectAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchingFragment : Fragment() {

    private lateinit var binding: FragmentSearchingBinding
    private lateinit var mainActionBar: MainActionBar

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_searching_1,
            R.string.tab_searching_2,
            R.string.tab_searching_3,
            )
        const val TAG = "SearchingFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchingBinding.inflate(inflater, container, false)
        setUpTabLayoutWithViewPager()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpActionBar()
    }

    override fun onStop() {
        super.onStop()
        mainActionBar.hideActionBar()
    }

    private fun setUpTabLayoutWithViewPager() {
        val sectionsPagerAdapter = SearchingSectionsPagerAdapter(requireActivity())
        val viewPager : ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

//        val ww = binding.tabLayout.width
//        Log.i(TAG, "width: $ww") // kok 0 HAHAHA

        for (i in 0..2) {
            val textView = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab, null) as TextView
            textView.text = getString(TAB_TITLES[i])
//            textView.width = ww / 3
            binding.tabLayout.getTabAt(i)?.customView = textView
        }
    }

    private fun setUpActionBar() {
        Log.d(TAG, "setUpActionBar: ")
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Pencarian")
        mainActionBar.setBackButton()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActionBar.hideActionBar()
    }
}