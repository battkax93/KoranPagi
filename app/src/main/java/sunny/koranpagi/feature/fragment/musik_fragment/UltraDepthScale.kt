package sunny.koranpagi.feature.fragment.musik_fragment

import android.support.v4.view.ViewCompat.setRotationY
import android.support.v4.view.ViewCompat.setScaleY
import android.support.v4.view.ViewCompat.setScaleX
import android.opengl.ETC1.getWidth
import android.support.v4.view.ViewCompat.setPivotX
import android.opengl.ETC1.getHeight
import android.support.v4.view.ViewCompat.setPivotY
import android.support.v4.view.ViewCompat.setTranslationX
import android.support.v4.view.ViewPager
import android.view.View


/**
 * Created by Wayan-MECS on 11/29/2018.
 */
class UltraDepthScale : ViewPager.PageTransformer {

    override fun transformPage(view: View, position: Float) {
        val scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position))
        val rotation = MAX_ROTATION * Math.abs(position)

        if (position <= 0f) {
            view.setTranslationX(view.getWidth() * -position * 0.19f)
            view.setPivotY(0.5f * view.getHeight())
            view.setPivotX(0.5f * view.getWidth())
            view.setScaleX(scale)
            view.setScaleY(scale)
            view.setRotationY(rotation)
        } else if (position <= 1f) {
            view.setTranslationX(view.getWidth() * -position * 0.19f)
            view.setPivotY(0.5f * view.getHeight())
            view.setPivotX(0.5f * view.getWidth())
            view.setScaleX(scale)
            view.setScaleY(scale)
            view.setRotationY(-rotation)
        }
    }

    companion object {
        private val MIN_SCALE = 0.5f
        private val MAX_ROTATION = 30f
    }
}