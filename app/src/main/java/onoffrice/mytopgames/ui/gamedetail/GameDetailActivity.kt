package onoffrice.mytopgames.ui.gamedetail

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_detail.*
import onoffrice.mytopgames.Constants
import onoffrice.mytopgames.R
import onoffrice.mytopgames.data.models.Game
import onoffrice.mytopgames.data.models.Top
import onoffrice.mytopgames.ui.base.BaseActivity
import onoffrice.mytopgames.utils.extension.loadPicasso
import org.jetbrains.anko.intentFor

class GameDetailActivity : BaseActivity() {

    private val topItem by lazy {
        intent.extras?.getSerializable(Constants.EXTRA_GAME_DETAIL) as Top
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_detail)
        setViews()
    }

    private fun setViews() {

        //Gets the top game
        var game = topItem.game ?: Game()

        //Sets info
        setToolbar(game.name ?: "",true)

        gameViewsCount.text    = topItem.viewers?.toString()
        gameChannelsCount.text = topItem.channels?.toString()

        //Loads the game poster using Picasso
        game.box?.large?.loadPicasso(gamePoster)
    }
}

fun Context.createGameDetailIntent(topItem: Top) = intentFor<GameDetailActivity>(Constants.EXTRA_GAME_DETAIL to topItem)