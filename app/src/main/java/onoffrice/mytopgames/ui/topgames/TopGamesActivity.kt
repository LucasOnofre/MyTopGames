package onoffrice.mytopgames.ui.topgames

import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top_games.*
import onoffrice.mytopgames.data.models.Game
import onoffrice.mytopgames.data.models.TopGamesList
import onoffrice.mytopgames.data.remote.TopGamesContract
import onoffrice.mytopgames.ui.adapter.GameClickListener
import onoffrice.mytopgames.ui.adapter.GamesAdapter


class TopGamesActivity : AppCompatActivity(), TopGamesContract.View{

    private val presenter: TopGamesContract.Presenter by lazy {
        TopGamesPresenter().apply {
            attachView(this@TopGamesActivity)
        }
    }

    private val gamesAdapter: GamesAdapter by lazy {
        val adapter = GamesAdapter(this, object : GameClickListener{
            override fun onClickGame(game: Game) {

            }
        })
        val layoutManager = GridLayoutManager(this, 2)
        topGamesRv.layoutManager = layoutManager
        topGamesRv.adapter = adapter
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onoffrice.mytopgames.R.layout.activity_top_games)
        presenter.getTopGames()

    }

    override fun displayLoading(loading: Boolean) {

    }

    override fun displayError(message: String?) {

    }

    override fun setTopGames(topGamesResponse: TopGamesList) {
        topGamesResponse.top?.let {
            gamesAdapter.list = it
        }
    }
}

