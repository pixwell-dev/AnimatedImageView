package sk.pixwell.android.widget.animatedimageview.animations

import android.graphics.Canvas

class ScaleAnimation(
        private val scaleMin: Float = 0.5f,
        private val scaleMax: Float = 1f
) : BaseAnimation() {
    private var scale = 1f
    private var direction = 1

    override fun draw(canvas: Canvas) {
        val dx = canvas.width / 2f
        val dy = canvas.height / 2f
        val scaleFactor = scale(0.01f)

        canvas.apply {
            translate(dx, dy)
            scale(scaleFactor, scaleFactor)
            translate(-dx, -dy)
        }
    }

    private fun scale(delta: Float): Float {
        scale += delta * direction
        if (scale <= scaleMin) {
            direction = 1
            scale = scaleMin
        } else if (scale >= scaleMax) {
            direction = -1
            scale = scaleMax
        }
        return scale
    }

    override fun reset() {
        super.reset()
        scale = 1f
        direction = 1
    }
}