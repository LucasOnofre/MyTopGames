package onoffrice.mytopgames.ui.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.adapter_movie_item.view.*
import onoffrice.mytopgames.R
import onoffrice.mytopgames.data.models.Top
import onoffrice.mytopgames.utils.extension.fadeUpItemListAnimation
import onoffrice.mytopgames.utils.extension.loadPicasso


interface GameClickListener {
   fun onClickGame(topItem: Top)
}

class GamesAdapter (
    private val context: Activity,
    private val listener:GameClickListener

    ): RecyclerView.Adapter<GamesAdapter.GameViewHolderItem>() {

    var list: MutableList<Top> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    /**
     * Return a movie list from the Discover
     */
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): GameViewHolderItem {

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

        val topItem = list[position]

        topItem.let {
            setPosterPath(topItem, holder)
            holder.title.text = topItem.game?.name

            holder.itemView.setOnClickListener {
                listener.onClickGame(topItem)
            }
        }
    }

    private fun setPosterPath(topItem: Top, holder: GameViewHolderItem) {
        val url = topItem.game?.box?.large
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
