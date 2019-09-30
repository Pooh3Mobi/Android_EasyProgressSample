package mobi.pooh3.easyprogressbar.ui.home

import android.animation.LayoutTransition
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.os.postDelayed
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_home.*
import mobi.pooh3.easyprogressbar.R

class HomeFragment : Fragment(), MyProgressBarControllerDelegate {
    override val handler: Handler by lazy { Handler(Looper.getMainLooper()) }
    override val progress0: View by lazy { progressView0 }
    override val progress1: View by lazy { progressView1 }

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.button_home).setOnClickListener {
            val action = HomeFragmentDirections
                .actionHomeFragmentToHomeSecondFragment("From HomeFragment")
            NavHostFragment.findNavController(this@HomeFragment)
                .navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        showMyProgress()
        handler.postDelayed(5_000L) {
            dismissMyProgress()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        destroyMyProgress()
    }
}