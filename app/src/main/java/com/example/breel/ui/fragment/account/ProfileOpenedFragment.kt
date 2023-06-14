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
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.account.adapter.UserExperienceAdapter
import com.example.breel.ui.fragment.account.adapter.UserProjectExperienceAdapter

class ProfileOpenedFragment : Fragment() {

    private lateinit var binding: FragmentProfileOpenedBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileOpenedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()

        val lstProject = getDummyListProjectExperience()
        val lstExperience = getDummyListExperience()

        val rvProject: RecyclerView = binding.rvProject
        val rvExperience: RecyclerView = binding.rvExperience

        rvProject.layoutManager = LinearLayoutManager(requireActivity())
        rvExperience.layoutManager = LinearLayoutManager(requireActivity())


        val adapterProject = UserProjectExperienceAdapter(lstProject)
        rvProject.adapter = adapterProject

        val adapterExperience = UserExperienceAdapter(lstExperience)
        rvExperience.adapter = adapterExperience

    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Akun")
    }

    private fun getDummyListProjectExperience() : List<UserProjectExperience> {
        return listOf<UserProjectExperience>(
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
            UserProjectExperience(
                "Lorem Ipsum 04",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 05",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 06",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 07",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
            UserProjectExperience(
                "Lorem Ipsum 08",
                "http://example.com/",
                getString(R.string.lorem_2sen)
            ),
        )
    }

    private fun getDummyListExperience() : List<UserExperience> {
        return listOf<UserExperience>(
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
            UserExperience(
                "Lorem",
                "Jakarta",
                "Lorem Ipsum 04",
                "01-01-2002",
                "07-07-2002",
                getString(R.string.lorem_2sen)
            ),
            UserExperience(
                "Lorem",
                "Jakarta",
                "Lorem Ipsum 05",
                "01-01-2002",
                "07-07-2002",
                getString(R.string.lorem_2sen)
            ),
        )
    }
}