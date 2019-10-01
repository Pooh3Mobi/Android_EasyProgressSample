# Android_EasyProgressSample
Easy Original Progress Sample Code. See activity_main.xml and MyBottomNavProgressControllerDelegate



## BottomNavIndicator pattern

<img src="https://user-images.githubusercontent.com/1821958/65977988-d5dcc000-e4ad-11e9-86bc-d938f7ddfcea.gif">

For animation codes.
1. `(this.parent as ViewGroup).layoutTransition.enableTransitionType(android.animation.LayoutTransition.CHANGING)`
2. `android:animateLayoutChanges="true"` attribution in `LinearLayout`.

```kotlin
interface MyBottomNavProgressControllerDelegate {
    val bottomNavProgressLeftPaddingView: View // weight_progress_left_padding
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
```
activity_main.xml
```xml
    <LinearLayout
        android:id="@+id/progressbar"
        android:layout_width="match_parent"
        android:layout_height="4dp"
        android:animateLayoutChanges="true"
        android:orientation="horizontal"
        android:weightSum="99"
        android:background="@android:color/darker_gray"
        android:layout_marginBottom="?attr/actionBarSize"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent">

        <View
            android:id="@+id/weight_progress_left_padding"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="0" />

        <View
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="33"
            android:background="@color/design_default_color_primary" />

    </LinearLayout>
```

## LoadingProgress pattern

<img src="https://user-images.githubusercontent.com/1821958/65977989-d5dcc000-e4ad-11e9-9a62-029fefff0576.gif" >

```kotlin
interface MyProgressBarControllerDelegate {
    val handler: Handler
    val progress0: View // progressView0
    val progress1: View // progressView1
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
```


```xml
    <LinearLayout
        android:id="@+id/progressbar"
        android:layout_width="0dp"
        android:layout_height="8dp"
        android:orientation="horizontal"
        android:weightSum="100"
        android:animateLayoutChanges="true"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        >

        <View
            android:id="@+id/progressView0"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="0" />

        <View
            android:id="@+id/progressView1"
            android:background="@android:color/holo_blue_dark"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="0" />

    </LinearLayout>
```
