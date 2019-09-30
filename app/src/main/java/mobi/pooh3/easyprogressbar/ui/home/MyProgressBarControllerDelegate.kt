package mobi.pooh3.easyprogressbar.ui.home

import android.animation.LayoutTransition
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.postDelayed

interface MyProgressBarControllerDelegate {
    val handler: Handler
    val progress0: View
    val progress1: View
    fun showMyProgress() = internalShowProgress(delay = 0L)

    fun dismissMyProgress() {
        (progress0.parent as View).visibility = View.GONE
        handler.removeCallbacksAndMessages(null)
    }

    fun destroyMyProgress() {
        handler.removeCallbacksAndMessages(null)
    }

    private fun internalShowProgress(count: Int = 0, delay: Long) {
        handler.postDelayed(delay) {
            when (count % 3) {
                0 -> {
                    changeProgress(progress0, 0f, needAnimate = true)
                    changeProgress(progress1, 100f, needAnimate = true)
                }
                1 -> {
                    changeProgress(progress0, 100f, needAnimate = true)
                    changeProgress(progress1, 0f, needAnimate = true)
                }
                2 -> {
                    changeProgress(progress0, 0f, needAnimate = false)
                    changeProgress(progress1, 0f, needAnimate = false)
                }
            }
            // next
            internalShowProgress(count + 1, 400L)
        }
    }

    private fun changeProgress(progressView: View, progressInt: Float, needAnimate: Boolean) {
        with(progressView) {
            val lp = layoutParams as LinearLayout.LayoutParams
            lp.weight = progressInt
            layoutParams = lp
            if (needAnimate) {
                (this.parent as ViewGroup).layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
            }
        }
    }
}