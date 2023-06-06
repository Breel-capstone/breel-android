package com.example.breel.ui.activity.testing

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.breel.R
import com.example.breel.databinding.ActivityTestBinding
import com.example.breel.ui.fragment.chat.MessageAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TestActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()

    private var _binding: ActivityTestBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.test()
        _binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)
        supportActionBar?.hide()
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)

        val dummyPosisi = "Video Editing dengan Michael Kusumawijaya"
        setBodyText(dummyPosisi)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setBodyText(posisi: String) {
        val str = SpannableStringBuilder(posisi)
        str.setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            posisi.lastIndex,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val temp: CharSequence = "Terima kasih atas ketertarikan dan partisipasi Anda terhadap " + str
        binding.tvBody.text = temp
        // masih belum sesuai expected.

        /*
        https://stackoverflow.com/questions/14371092/how-to-make-a-specific-text-on-textview-bold
        https://stackoverflow.com/questions/11479560/custom-textview-in-android-with-different-color-words
        https://stackoverflow.com/questions/43663324/concatenate-spannable-string-and-string
         */
    }
}