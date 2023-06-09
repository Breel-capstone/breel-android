package com.example.breel.ui.fragment.notification.detail

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.breel.databinding.FragmentProposalAcceptedBinding
import com.example.breel.ui.component.MainActionBar


class ProposalAcceptedFragment : Fragment() {

    private lateinit var binding: FragmentProposalAcceptedBinding
    private lateinit var mainActionBar: MainActionBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProposalAcceptedBinding.inflate(inflater, container, false)
        setUpActionBar()
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dummyPosisi = "Web Development dengan Agnes Monika"
        setBodyText(dummyPosisi)
    }

    private fun setUpActionBar() {
        mainActionBar = MainActionBar(this)
        mainActionBar.setTitle("Proposal Freelance")
        mainActionBar.setBackButton()
    }

    private fun setBodyText(posisi: String) {
        val str = SpannableStringBuilder(posisi)
        str.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            posisi.lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val temp: CharSequence = "untuk posisi " + str + ". Pelajari dulu tawaran ini sebelum merespon, ya!"
        binding.tvBody.text = temp
        // masih belum sesuai expected.

        /*
        https://stackoverflow.com/questions/14371092/how-to-make-a-specific-text-on-textview-bold
        https://stackoverflow.com/questions/11479560/custom-textview-in-android-with-different-color-words
        https://stackoverflow.com/questions/43663324/concatenate-spannable-string-and-string
         */
    }
}