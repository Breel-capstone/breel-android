package com.example.breel.ui.fragment.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.databinding.FragmentProfileOpenedBinding
import com.example.breel.ui.fragment.account.adapter.UserExperienceAdapter
import com.example.breel.ui.fragment.account.adapter.UserProjectExperienceAdapter

class ProfileOpenedFragment : Fragment() {

    private lateinit var binding: FragmentProfileOpenedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_opened, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvExperience: RecyclerView = binding.rvExperience
        rvExperience.layoutManager = LinearLayoutManager(requireActivity())

        val lst = listOf<UserExperience>(
            UserExperience(
                "Lorem",
                "Jakarta",
                "Lorem Ipsum 01",
                "01-01-2002",
                "07-07-2002",
                getString(R.string.lorem_2sen)
            ),
            UserExperience(
                "Lorem",
                "Jakarta",
                "Lorem Ipsum 02",
                "01-01-2002",
                "07-07-2002",
                getString(R.string.lorem_2sen)
            ),
            UserExperience(
                "Lorem",
                "Jakarta",
                "Lorem Ipsum 03",
                "01-01-2002",
                "07-07-2002",
                getString(R.string.lorem_2sen)
            ),
        )

        val adapter = UserExperienceAdapter(lst)
        rvExperience.adapter = adapter

        val rvProject: RecyclerView = binding.rvProject
        rvProject.layoutManager = LinearLayoutManager(requireActivity())

        val lst2 = listOf<UserProjectExperience>(
            UserProjectExperience(
                "Lorem Ipsum 01",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 02",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 03",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
        )

        val adapter2 = UserProjectExperienceAdapter(lst2)
        rvExperience.adapter = adapter2
    }
}