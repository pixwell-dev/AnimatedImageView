package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.view.animation.BaseInterpolator
import android.view.animation.LinearInterpolator

class RotateAnimation(
    duration: Int = 2000,
    oneShot: Boolean = false,
    interpolator: BaseInterpolator = LinearInterpolator()
) : BaseAnimation(duration, oneShot, interpolator) {
    override val order = 1

    override fun draw(canvas: Canvas, progress: Float, interpolation: Float) {
        val dx = canvas.width / 2f
        val dy = canvas.height / 2f

        canvas.apply {
            translate(dx, dy)
            rotate(360 * interpolation)
            translate(-dx, -dy)
        }
    }
}