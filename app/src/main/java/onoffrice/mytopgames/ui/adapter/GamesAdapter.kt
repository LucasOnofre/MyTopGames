package onoffrice.mytopgames.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_movie_item.view.*
import onoffrice.mytopgames.R
import onoffrice.mytopgames.data.models.Game
import onoffrice.mytopgames.data.models.Top
import onoffrice.mytopgames.utils.extension.loadPicasso


interface GameClickListener {
   fun onClickGame(game: Game)
}

class GamesAdapter (
    private val context: Activity,
    private val listener:GameClickListener
    //private val listenerLongClickInterface:MovieLongClickInterface

    ): RecyclerView.Adapter<GamesAdapter.GameViewHolderItem>() {

    var list: List<Top> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Return a movie list from the Discover
     */
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GamesAdapter.GameViewHolderItem {

        var view: View =
            LayoutInflater.from(context).inflate(R.layout.adapter_movie_item, parent, false)

        return GameViewHolderItem(view)
    }

    /**
     * Return a movie list from the Discover
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Makes the bind for every item in the view
     */
    override fun onBindViewHolder(holder: GameViewHolderItem, position: Int) {

        val game = list[position].game

        game?.let {
            setPosterPath(it, holder)
            holder.title.text = game.name

            holder.itemView.setOnClickListener {
                listener.onClickGame(game)
            }
        }
    }

    private fun setPosterPath(game: Game, holder: GameViewHolderItem) {

        val url = game.box?.large
        url?.loadPicasso(holder.poster)
    }

    /**
     * Create a viewHolder
     */
    class GameViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val poster = itemView.poster!!
        val title = itemView.title!!
        //val noImageWarner= itemView.noImageWarn

    }
}

//    /**
//     * Set's the widht and heigh of the movie poster according to the screen size
//     */
//    private fun getScreenSize(itemView: View) {
//
//        //Get's the screen size and return a triple
//        var (orientation, width, height) = context.getScreenSize()
//
//        itemView.movie_poster.maxWidth  = (width / 2)
//
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
//
//            itemView.movie_poster.layoutParams.height = (height * 2) / 7
//
//        }else
//            itemView.movie_poster.layoutParams.height = (height * 2) / 7
//    }
//}
//
//fun Activity.getScreenSize(): Triple<Int, Int, Int> {
//    var orientation = this.resources.configuration.orientation
//
//    val displayMetrics = DisplayMetrics()
//    this.windowManager.defaultDisplay.getMetrics(displayMetrics)
//
//    var width  = displayMetrics.widthPixels
//    var height = displayMetrics.heightPixels
//
//    return Triple(orientation, width, height)
//}
