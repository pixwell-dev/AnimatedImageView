package sk.pixwell.android.widget.animatedimageview

import android.content.Context
import android.graphics.Canvas
import android.os.Build
import android.util.AttributeSet
import android.widget.ImageView
import sk.pixwell.android.widget.animatedimageview.animations.BaseAnimation

open class AnimatedImageView : ImageView {
    private val animations = mutableListOf<BaseAnimation>()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @Suppress("UNUSED_PARAMETER")
    private fun init(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int? = null) {

    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            animations.forEach { it.draw(canvas) }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation()
            }
        }

        super.onDraw(canvas)
    }

    fun addAnimation(animation: BaseAnimation) {
        if (!hasAnimation(animation)) {
            animations.add(animation)
            resetAnimations()
        }
    }

    fun removeAnimation(animation: BaseAnimation) {
        animations.removeAll { it == animation }
    }

    fun removeAllAnimations() {
        animations.clear()
    }

    fun hasAnimation(animation: BaseAnimation): Boolean {
        return animations.filter { it == animation }.isNotEmpty()
    }

    fun resetAnimations() {
        animations.forEach { it.reset() }
    }
}