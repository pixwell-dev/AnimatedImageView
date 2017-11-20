package sk.pixwell.android.widget.animatedimageview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.widget.ImageView
import sk.pixwell.android.widget.animatedimageview.animation.BaseAnimation

open class AnimatedImageView : ImageView {
    private val animations = mutableListOf<BaseAnimation>()
    var onAnimationCompleteListener: ((AnimatedImageView, BaseAnimation) -> Unit)? = null
    var onAllAnimationsCompleteListener: ((AnimatedImageView) -> Unit)? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null) {
            animations.forEach { it.draw(canvas) }
            postInvalidateOnAnimation()
        }

        super.onDraw(canvas)
    }

    fun addAnimation(animation: BaseAnimation) {
        if (!hasAnimation(animation)) {
            animation.onCompleteListener = fun(a) {
                onAnimationCompleteListener?.invoke(this, a)
                if (animations.all { it.isCompleted }) {
                    onAllAnimationsCompleteListener?.invoke(this)
                }
            }
            animations.apply {
                add(animation)
                sortBy { it.order }
            }
            resetAnimations()
        }
    }

    @Suppress("unused")
    fun removeAnimation(animation: BaseAnimation) {
        animations.removeAll { it == animation }
    }

    fun removeAllAnimations() {
        animations.clear()
    }

    fun hasAnimation(animation: BaseAnimation): Boolean {
        return animations.any { it == animation }
    }

    fun resetAnimations() {
        animations.forEach { it.reset() }
    }
}