package onoffrice.mytopgames.utils.extension

import android.view.View
import android.view.animation.AnimationUtils
import onoffrice.mytopgames.R

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun View.fadeUpItemListAnimation(position: Int, animationDelay: Long) {
    val firstPositionDelay = 3
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_up_item)
    animation.startOffset = (position + firstPositionDelay) * animationDelay

    this.animation = animation
}