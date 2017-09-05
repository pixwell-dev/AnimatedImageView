package sk.pixwell.android.widget.animatedimageview.animations

import android.graphics.Canvas

abstract class BaseAnimation(var oneShot: Boolean) {
    abstract fun draw(canvas: Canvas)

    open fun reset() {

    }

    override fun equals(other: Any?): Boolean = other != null && this::class == other::class
}