package com.example.breel.ui.fragment.authentication.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.breel.R
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.databinding.FragmentRegisterDetailBinding
import com.example.breel.ui.activity.main.MainActionBar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDetailFragment : Fragment() {
    private var _binding: FragmentRegisterDetailBinding? = null
    private val binding: FragmentRegisterDetailBinding get() = _binding!!
    private lateinit var mainActionBar: MainActionBar
    private val projectDataList = mutableListOf<UserProjectExperience>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpActionBar()
        setListener()
    }

    private fun setListener() {
        binding.addProjectButton.setOnClickListener {
            addProject()
        }
        binding.submitButton.setOnClickListener {
            getProjectsData()


            // Log the project data
            for (projectData in projectDataList) {
                Log.d("FormSubmission", "$projectData")
            }
        }
    }

    private fun getProjectsData() {
        for (i in 0 until binding.projectLayout.childCount) {
            val projectView = binding.projectLayout.getChildAt(i)
            val positionEditText =
                projectView.findViewById<TextInputEditText>(R.id.positionEditText)
            val thumbnailEditText =
                projectView.findViewById<TextInputEditText>(R.id.thumbnailEditText)
            val descriptionEditText =
                projectView.findViewById<TextInputEditText>(R.id.descriptionEditText)

            val position = positionEditText.text.toString()
            val link = thumbnailEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val projectData = UserProjectExperience(position, link, description)
            projectDataList.add(projectData)
        }
    }

    private fun addProject() {
        val projectView = layoutInflater.inflate(R.layout.item_profile_project_layout, null)
        val projectNumber = projectView.findViewById<TextView>(R.id.projectNumberTextView)
        projectNumber.text = "Proyek ${projectDataList.size + 1}"
        binding.projectLayout.addView(projectView)
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Tambah Detail")
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}