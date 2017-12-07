package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.graphics.RectF
import android.os.Build
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator

class AlphaAnimation(
        duration: Int = 2000,
        oneShot: Boolean = false,
        interpolator: Interpolator = LinearInterpolator(),
        var fromAlpha: Int = 0,
        var toAlpha: Int = 255,
        var reverseRepeat: Boolean = true
) : BaseAnimation(duration, oneShot, interpolator) {
    override fun draw(canvas: Canvas, progress: Float, interpolation: Float) {
        val rect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())

        var alpha = if (reverseRepeat) {
            if (progress <= 0.5) {
                2 * interpolation * (toAlpha - fromAlpha) + fromAlpha
            } else {
                2 * (1 - interpolation) * (toAlpha - fromAlpha) + fromAlpha
            }
        } else {
            fromAlpha + interpolation * (toAlpha - fromAlpha)
        }

        canvas.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                saveLayerAlpha(rect, alpha.toInt())
            } else {
                @Suppress("DEPRECATION")
                saveLayerAlpha(rect, alpha.toInt(), Canvas.ALL_SAVE_FLAG)
            }
        }
    }
}