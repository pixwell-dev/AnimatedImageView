package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.view.animation.BaseInterpolator
import android.view.animation.LinearInterpolator

class ScaleAnimation(
    duration: Int = 2000,
    oneShot: Boolean = false,
    interpolator: BaseInterpolator = LinearInterpolator(),
    var minScale: Float = 0.5f,
    var maxScale: Float = 1f
) : BaseAnimation(duration, oneShot, interpolator) {
    override fun draw(canvas: Canvas, progress: Float, interpolation: Float) {
        val dx = canvas.width / 2f
        val dy = canvas.height / 2f

        val p = if (progress <= 0.5) interpolation else (1 - interpolation)
        val scale = 2 * p * (maxScale - minScale) + minScale

        canvas.apply {
            translate(dx, dy)
            scale(scale, scale)
            translate(-dx, -dy)
        }
    }
}