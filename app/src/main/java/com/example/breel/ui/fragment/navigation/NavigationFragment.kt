package com.example.breel.ui.fragment.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.breel.R
import com.example.breel.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {


    private var _binding: FragmentNavigationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNavigationBinding.inflate(inflater, container, false)
        setViewPager()
        return binding.root
    }

    private fun setViewPager() {
        val adapter = NavigationPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_beranda -> binding.viewPager.setCurrentItem(0, true)
                R.id.menu_chat -> binding.viewPager.setCurrentItem(1, true)
                R.id.menu_mentor -> binding.viewPager.setCurrentItem(2, true)
                R.id.menu_notifikasi -> binding.viewPager.setCurrentItem(3, true)
                R.id.menu_akun -> binding.viewPager.setCurrentItem(4, true)
            }
            true
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bottomNav.menu.getItem(position).isChecked = true
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}