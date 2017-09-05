package sk.pixwell.android.widget.animatedimageview.animations

import android.graphics.Canvas

class JumpAnimation(val height: Double = 0.3, oneShot: Boolean = false) : BaseAnimation(oneShot) {
    private var translation = 0
    private var direction = -1
    private var maxTranslation: Int = -1

    override fun draw(canvas: Canvas) {
        if (maxTranslation == -1) {
            maxTranslation = (canvas.height * height).toInt()
        }

        canvas.apply {
            translate(0f, jump(5).toFloat())
        }
    }

    private fun jump(delta: Int): Int {
        translation += delta * direction
        if (Math.abs(translation) >= maxTranslation) {
            direction = 1
            translation = -maxTranslation
        } else if (translation > 0) {
            direction = -1
            translation = 0
        }
        return translation
    }

    override fun reset() {
        super.reset()
        translation = 0
        direction = -1
    }
}
