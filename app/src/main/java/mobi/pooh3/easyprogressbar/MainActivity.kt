package mobi.pooh3.easyprogressbar

import android.animation.LayoutTransition
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import android.view.ViewGroup


class MainActivity : AppCompatActivity() {
    val navSet =
        setOf(R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(navSet)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            // change progress here
            val index = navSet.indexOf(destination.id)
            changeProgress((index) * 33f)
        }
    }

    private fun changeProgress(progressInt: Float) {
        with(weight_progress_left_padding) {
            val lp = layoutParams as LinearLayout.LayoutParams
            lp.weight = progressInt
            layoutParams = lp

            (this.parent as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        }
    }
}
