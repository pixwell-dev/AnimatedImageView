package sk.pixwell.android.widget.animatedimageview.animation.interpolator

import android.view.animation.Interpolator

class DecelerateAccelerateInterpolator : Interpolator {
    override fun getInterpolation(p0: Float): Float {
        return ((1 - Math.pow(1 - (2 * p0).toDouble(), 3.0)) / 2).toFloat()
    }
}