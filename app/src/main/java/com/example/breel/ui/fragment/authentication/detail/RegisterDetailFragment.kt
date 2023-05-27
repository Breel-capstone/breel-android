package com.example.breel.ui.fragment.authentication.detail

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.breel.R
import com.example.breel.data.Resource
import com.example.breel.data.api.BackendResponseNoData
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.data.api.user.detail.UserSkill
import com.example.breel.databinding.FragmentRegisterDetailBinding
import com.example.breel.ui.activity.main.MainActionBar
import com.example.breel.ui.component.StatusSnackBar
import com.google.android.material.chip.Chip
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterDetailFragment : Fragment() {
    private var _binding: FragmentRegisterDetailBinding? = null
    private val binding: FragmentRegisterDetailBinding get() = _binding!!
    private lateinit var mainActionBar: MainActionBar
    private val projectDataList = mutableListOf<UserProjectExperience>()
    private val experienceDataList = mutableListOf<UserExperience>()
    private val skillDataList = mutableListOf<UserSkill>()
    private var projectCounter = 1
    private var experienceCounter = 1
    private val viewModel: RegisterDetailViewModel by viewModels()
    private lateinit var statusSnackBar: StatusSnackBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statusSnackBar = StatusSnackBar(view)
        setUpActionBar()
        setListener()
        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.registerDetailResultLiveData.observe(viewLifecycleOwner) {
            handleRegisterDetail(it)
        }
    }

    private fun handleRegisterDetail(status: Resource<BackendResponseNoData>) {

        when (status) {
            is Resource.Success -> status.data?.let {
                statusSnackBar.showSuccess(it.message.title)
            }

            is Resource.DataError -> {
                status.errorMessage?.let { statusSnackBar.showError(it) }
            }

            else -> Toast.makeText(requireContext(), "Loading", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setListener() {
        binding.skillTextField.inputType = InputType.TYPE_CLASS_TEXT
        binding.skillTextField.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addSkill()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.addProjectButton.setOnClickListener {
            addProject()
        }
        binding.addExperienceButton.setOnClickListener {
            addExperience()
        }
        binding.submitButton.setOnClickListener {
            val fullName = binding.nameEditText.text.toString()
            val position = binding.positionEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()
            getProjectsData()
            getExperiencesData()
            viewModel.registerDetail(
                fullName,
                position,
                description,
                experienceDataList,
                skillDataList,
                projectDataList
            )
        }
    }

    private fun addSkill() {
        val skillView = layoutInflater.inflate(R.layout.item_profile_chip_skill, null) as Chip
        val enteredText = binding.skillTextField.text.toString()
        val userSkill = UserSkill(enteredText)
        skillDataList.add(userSkill)
        skillView.text = enteredText
        binding.skillLayout.addView(skillView)

        binding.skillTextField.setText("")
        binding.skillTextField.clearFocus()
        binding.skillTextField.clearComposingText()
    }

    private fun getExperiencesData() {
        for (i in 0 until binding.experienceLayout.childCount) {
            val experienceView = binding.experienceLayout.getChildAt(i)
            val companyEditText =
                experienceView.findViewById<TextInputEditText>(R.id.companyNameEditText)
            val locationEditText =
                experienceView.findViewById<TextInputEditText>(R.id.locationEditText)
            val positionEditText =
                experienceView.findViewById<TextInputEditText>(R.id.positionEditText)
            val startDateEditText =
                experienceView.findViewById<TextInputEditText>(R.id.startDateEditText)
            val endDateEditText =
                experienceView.findViewById<TextInputEditText>(R.id.endDateEditText)
            val descriptionEditText =
                experienceView.findViewById<TextInputEditText>(R.id.descriptionEditText)

            val company = companyEditText.text.toString()
            val position = positionEditText.text.toString()
            val location = locationEditText.text.toString()
            val startDate = startDateEditText.text.toString()
            val endDate = endDateEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val experienceData =
                UserExperience(company, location, position, startDate, endDate, description)
            experienceDataList.add(experienceData)
        }
    }


    private fun addExperience() {
        val projectView = layoutInflater.inflate(R.layout.item_profile_experience_layout, null)
        val projectNumber = projectView.findViewById<TextView>(R.id.experienceNumberTextView)
        projectNumber.text = "Pengalaman ${experienceCounter}"
        binding.experienceLayout.addView(projectView)
        experienceCounter++
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
        projectNumber.text = "Proyek ${projectCounter}"
        binding.projectLayout.addView(projectView)
        projectCounter++
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