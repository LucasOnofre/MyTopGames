package onoffrice.mytopgames.utils.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Load's the String URL in the Image View param
 */
fun String.loadPicasso(local: ImageView?){
    Picasso.get().load(this).into(local)
}