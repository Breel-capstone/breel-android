package com.example.breel.ui.fragment.searching.mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.mentor.Mentor
import com.example.breel.databinding.FragmentMentorTabBinding
import com.example.breel.ui.fragment.home.mentor.MentorAdapter
import com.example.breel.ui.fragment.navigation.NavigationFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MentorTabFragment : Fragment() {

    private lateinit var binding: FragmentMentorTabBinding
    private val viewModel: MentorViewModel by viewModels()
    private lateinit var mentorAdapter: MentorAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorTabBinding.inflate(inflater, container, false)
        setUpRecyclerView()
        observeViewModel()
        return binding.root
    }

    private fun setUpRecyclerView() {
        setMentor()
    }

    private fun setMentor() {
        val rv: RecyclerView = binding.rvMentor
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        mentorAdapter = MentorAdapter {
            val destination = NavigationFragmentDirections.actionNavigationFragment2ToMentorDetailFragment(it)
            findNavController().navigate(destination)
        }
        rv.adapter = mentorAdapter
    }

    private fun observeViewModel() {
        viewModel.getMentors()
        viewModel.mentorPagingLiveData.observe(viewLifecycleOwner) {
            handleMentorResult(it)
        }
    }

    private fun handleMentorResult(pagingData: PagingData<Mentor>) {
        lifecycleScope.launch {
            mentorAdapter.submitData(pagingData)
        }
    }

}