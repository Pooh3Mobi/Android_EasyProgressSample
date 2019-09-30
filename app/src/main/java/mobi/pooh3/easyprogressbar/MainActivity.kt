package mobi.pooh3.easyprogressbar

import android.os.Bundle
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(),
    MyBottomNavProgressControllerDelegate {
    override val bottomNavProgressLeftPaddingView: View by lazy { weight_progress_left_padding }
    private val navSet = setOf(
        R.id.navigation_home,
        R.id.navigation_dashboard,
        R.id.navigation_notifications
    )

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
            changeBottomNavProgress(index)
        }
    }
}
