package mobi.pooh3.easyprogressbar

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

interface MyBottomNavProgressControllerDelegate {
    val bottomNavProgressLeftPaddingView: View
    fun changeBottomNavProgress(index: Int) {
        if (index < 0) return
        val progress = (index) * 33f
        with(bottomNavProgressLeftPaddingView) {
            val lp = layoutParams as LinearLayout.LayoutParams
            lp.weight = progress
            layoutParams = lp
            (this.parent as ViewGroup).layoutTransition.enableTransitionType(android.animation.LayoutTransition.CHANGING)
        }
    }
}