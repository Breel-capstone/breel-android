import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.breel.R
import com.example.breel.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private val splashDelay: Long = 2000 // Delay in milliseconds
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startSplashTimer()
    }

    private fun startSplashTimer() {
        Handler(Looper.getMainLooper()).postDelayed({
            // Navigate to the desired destination after the splash delay
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        }, splashDelay)
    }
}