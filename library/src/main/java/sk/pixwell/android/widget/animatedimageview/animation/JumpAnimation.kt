package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.view.animation.Interpolator
import sk.pixwell.android.widget.animatedimageview.animation.interpolator.DecelerateAccelerateInterpolator

class JumpAnimation(
        duration: Int = 2000,
        oneShot: Boolean = false,
        interpolator: Interpolator = DecelerateAccelerateInterpolator(),
        var height: Double = 0.3
) : BaseAnimation(duration, oneShot, interpolator) {
    override fun draw(canvas: Canvas, progress: Float, interpolation: Float) {
        val dym = (-canvas.height * height).toInt()

        val dy = if (progress <= 0.5) 2 * interpolation * dym else 2 * (1 - interpolation) * dym

        canvas.apply {
            translate(0f, dy)
        }
    }
}
