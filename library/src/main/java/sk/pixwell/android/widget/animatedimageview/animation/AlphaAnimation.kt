package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.graphics.RectF
import android.os.Build
import android.view.animation.BaseInterpolator
import android.view.animation.LinearInterpolator

class AlphaAnimation(
    duration: Int = 2000,
    oneShot: Boolean = false,
    interpolator: BaseInterpolator = LinearInterpolator(),
    var minAlpha: Int = 0,
    var maxAlpha: Int = 255
) : BaseAnimation(duration, oneShot, interpolator) {
    override fun draw(canvas: Canvas, progress: Float, interpolation: Float) {
        val rect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())

        val p = if (progress <= 0.5) interpolation else (1 - interpolation)
        val alpha = 2 * p * (maxAlpha - minAlpha) + minAlpha

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