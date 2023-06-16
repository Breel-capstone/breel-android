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
import com.google.api.Distribution.BucketOptions.Linear

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

        val lstFreelance = getDummyFreelance()
        val lstProject = getDummyListProjectExperience()
        val lstExperience = getDummyListExperience()

        val rvFreelance: RecyclerView = binding.rvFreelance
        val rvProject: RecyclerView = binding.rvProject
        val rvExperience: RecyclerView = binding.rvExperience

        rvFreelance.layoutManager = LinearLayoutManager(requireActivity())
        rvProject.layoutManager = LinearLayoutManager(requireActivity())
        rvExperience.layoutManager = LinearLayoutManager(requireActivity())

        val adapterFreelance = UserProjectExperienceAdapter(lstFreelance)
        rvFreelance.adapter = adapterFreelance

        val adapterProject = UserProjectExperienceAdapter(lstProject)
        rvProject.adapter = adapterProject

        val adapterExperience = UserExperienceAdapter(lstExperience)
        rvExperience.adapter = adapterExperience

    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Akun")
    }

    private fun getDummyFreelance() : List<UserProjectExperience> {
        return listOf<UserProjectExperience>(
            UserProjectExperience(
                "Company Profile Website",
                "http://example.com/",
                "Website sederhana berisi informasi mengenai sebuah perusahaan."
            ),
        )
    }

    private fun getDummyListProjectExperience() : List<UserProjectExperience> {
        return listOf<UserProjectExperience>(
            UserProjectExperience(
                "Running Tracker App",
                "http://example.com/",
                "Mengimplementasikan konsep Geolocation, aplikasi ini melakukan penggambaran rute berlari user. Data histori disimpan di local storage pengguna."
            ),
            UserProjectExperience(
                "Online-Offline To Do List App",
                "http://example.com/",
                "Mengimplementasikan online-offline app, aplikasi ini melakukan penyimpanan data di remote server dan juga melakukan caching."
            ),
        )
    }

    private fun getDummyListExperience() : List<UserExperience> {
        return listOf<UserExperience>(
            UserExperience(
                "Bangkit Capstone",
                "Online",
                "Project Manager",
                "April 2023",
                "Juli 2023",
                "Menjadi Project Manager untuk tim Product Capstone program Bangkit 2023. Memimpin dan mengatur anggota untuk mengerjakan tugas sesuai Learning Path yang mereka pelajari."
            ),
            UserExperience(
                "PIPTEK",
                "Jakarta",
                "Lorem Ipsum 02",
                "Januari 2022",
                "Juli 2022",
                "Bertanggung jawab sebagai Ketua Bidang PIPTEK IME FTUI. Mendukung warga DTE serta anggota untuk melakukan riset dan perlombaan yang sesuai core competence Elektro."
            ),
        )
    }
}