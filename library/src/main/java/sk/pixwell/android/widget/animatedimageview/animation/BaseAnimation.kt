package sk.pixwell.android.widget.animatedimageview.animation

import android.graphics.Canvas
import android.view.animation.BaseInterpolator
import android.view.animation.LinearInterpolator

abstract class BaseAnimation(
    var duration: Int = 2000,
    var oneShot: Boolean = false,
    var interpolator: BaseInterpolator = LinearInterpolator()
) {
    private var startFrameTime: Long = -1
    private var endFrameTime: Long = -1
    var isCompleted = false
    var onCompleteListener: ((BaseAnimation) -> Unit)? = null
    open val order = 0

    override fun equals(other: Any?): Boolean
        = other != null && this::class == other::class

    fun reset() {
        startFrameTime = -1
        endFrameTime = -1
        isCompleted = false
    }

    fun draw(canvas: Canvas) {
        if (oneShot && isCompleted) {
            onCompleteListener?.invoke(this)
            return
        }

        val currentFrameTime = System.currentTimeMillis()

        if (endFrameTime != -1L && currentFrameTime >= endFrameTime) {
            reset()
            isCompleted = true
        }

        if (startFrameTime == -1L) {
            startFrameTime = currentFrameTime
            endFrameTime = startFrameTime + duration
        }

        val progress = (currentFrameTime - startFrameTime) / duration.toFloat()

        draw(canvas, progress, interpolator.getInterpolation(progress))
    }

    abstract fun draw(canvas: Canvas, progress: Float, interpolation: Float)
}