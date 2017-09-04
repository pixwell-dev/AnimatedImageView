package sk.pixwell.android.widget.animatedimageview.animations

import android.graphics.Canvas

class RotateAnimation : BaseAnimation() {
    private var degrees = 0

    override fun draw(canvas: Canvas) {
        val dx = canvas.width / 2f
        val dy = canvas.height / 2f

        canvas.apply {
            translate(dx, dy)
            rotate(rotation(3).toFloat())
            translate(-dx, -dy)
        }
    }

    private fun rotation(delta: Int): Int {
        degrees = (degrees + delta) % 360
        return degrees
    }

    override fun reset() {
        super.reset()
        degrees = 0
    }
}