package onoffrice.mytopgames.ui.topgames

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top_games.*
import onoffrice.mytopgames.data.models.Game
import onoffrice.mytopgames.data.models.Top
import onoffrice.mytopgames.data.models.TopGamesList
import onoffrice.mytopgames.data.remote.TopGamesContract
import onoffrice.mytopgames.ui.adapter.GameClickListener
import onoffrice.mytopgames.ui.adapter.GamesAdapter
import onoffrice.mytopgames.ui.gamedetail.createGameDetailIntent
import onoffrice.mytopgames.utils.extension.setVisible
import onoffrice.mytopgames.utils.extension.startActivitySlideTransition


class TopGamesActivity : AppCompatActivity(), TopGamesContract.View{

    private var offset = 0

    private val presenter: TopGamesContract.Presenter by lazy {
        TopGamesPresenter().apply {
            attachView(this@TopGamesActivity)
        }
    }

    private val gamesAdapter: GamesAdapter by lazy {
        val adapter = GamesAdapter(this, object : GameClickListener{
            override fun onClickGame(topItem: Top) {
                startActivitySlideTransition(createGameDetailIntent(topItem))
            }
        })

        val layoutManager        = GridLayoutManager(this, 2)
        topGamesRv.layoutManager = layoutManager
        topGamesRv.adapter       = adapter
        adapter
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onoffrice.mytopgames.R.layout.activity_top_games)
        setListeners()
        presenter.getTopGames(offset)
    }

    private fun setListeners() {
        //Pull to refresh listener
        swipeRefresh.setOnRefreshListener {
            presenter.getTopGames(offset)
        }

        setInfiniteScroll()
    }

    override fun displayLoading(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
    }

    override fun displayError(message: String?) {

    }

    override fun setTopGames(topGamesResponse: TopGamesList) {
        topGamesResponse.top?.let {
            if (gamesAdapter.list.isEmpty())
                gamesAdapter.list = it.toMutableList()
            else
                gamesAdapter.list.addAll(it)
                gamesAdapter.notifyDataSetChanged()
        }
    }

    /** Make's new requests when user scrolls the list to the last item */
    private fun setInfiniteScroll() {
        var isLoading: Boolean = false
        topGamesRv?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //direction = 1 = list ends
                if (!recyclerView.canScrollVertically(1 ) && !isLoading){
                    isLoading = true
                    offset += 10
                    presenter.getTopGames(offset)
                    isLoading = false
                }
            }
        })
    }
}

