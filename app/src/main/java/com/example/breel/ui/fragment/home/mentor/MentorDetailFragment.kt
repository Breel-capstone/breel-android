package com.example.breel.ui.fragment.home.mentor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.data.api.user.detail.UserExperience
import com.example.breel.data.api.user.detail.UserProjectExperience
import com.example.breel.databinding.FragmentMentorDetailBinding
import com.example.breel.ui.component.MainActionBar
import com.example.breel.ui.fragment.account.adapter.UserExperienceAdapter
import com.example.breel.ui.fragment.account.adapter.UserProjectExperienceAdapter

class MentorDetailFragment : Fragment() {

    private lateinit var binding: FragmentMentorDetailBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMentorDetailBinding.inflate(inflater, container, false)
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
        mainActionBar.setTitle("Detail Mentor")
    }

    private fun getDummyFreelance() : List<UserProjectExperience> {
        return listOf<UserProjectExperience>(
            UserProjectExperience(
                "MIS Dashboard",
                "http://example.com/",
                "Mengolah data dan menyajikannya dalam bentuk tabel dan grafik untuk keperluan pengambilan keputusan manajerial."
            ),
            UserProjectExperience(
                "Chatting Feature",
                "http://example.com/",
                "Mengimplementasikan fitur chatting di sebuah software perusahaan dengan menggunakan firestore."
            ),
        )
    }

    private fun getDummyListProjectExperience() : List<UserProjectExperience> {
        return listOf<UserProjectExperience>(
            UserProjectExperience(
                "Filter Instagram",
                "http://example.com/",
                "Proyek yang menggunakan image recognition. Filter ini dapat dengan bebas digunakan."
            ),
            UserProjectExperience(
                "Covid Statistik",
                "http://example.com/",
                "Aplikasi yang mengizinkan penggunanya memasang widget grafik statistik Covid di Indonesia. Data terupdate secara berkala. "
            ),
        )
    }

    private fun getDummyListExperience() : List<UserExperience> {
        return listOf<UserExperience>(
            UserExperience(
                "EduCare",
                "South Jakarta",
                "e-Learning App Developer",
                "Januari 2022",
                "Desember 2022",
                "Applikasi yang dilengkapi dengan personalisasi dan gamifikasi, serta memanfaatkan animasi untuk menghasilkan pengalaman pengguna yang sebaik-baiknya. Di-download oleh lebih dari 10k pengguna di Google Play Store."
            ),
            UserExperience(
                "EduCare",
                "South Jakarta",
                "IT Project Manager",
                "Januari 2023",
                "Maret 2023",
                "Memimpin squad berisi 8 orang dengan 4 role untuk meng-improve performa aplikasi."
            ),
        )
    }
}