package sk.pixwell.android.widget.animatedimageview.animations

import android.graphics.Canvas
import android.graphics.RectF
import android.os.Build

class AlphaAnimation(
    val minAlpha: Int = 0,
    val maxAlpha: Int = 255,
    oneShot: Boolean = false
) : BaseAnimation(oneShot) {
    private var alpha = 255
    private var direction = -1

    override fun draw(canvas: Canvas) {
        val rect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.saveLayerAlpha(rect, alpha(5))
        } else {
            canvas.saveLayerAlpha(rect, alpha(5), Canvas.ALL_SAVE_FLAG)
        }
    }

    private fun alpha(delta: Int): Int {
        alpha += delta * direction
        if (alpha <= minAlpha) {
            direction = 1
            alpha = minAlpha
        } else if (alpha >= maxAlpha) {
            direction = -1
            alpha = maxAlpha
        }
        return alpha
    }

    override fun reset() {
        super.reset()
        alpha = 255
        direction = -1
    }
}