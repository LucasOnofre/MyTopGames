package onoffrice.mytopgames.ui.topgames

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_top_games.*
import onoffrice.mytopgames.Constants
import onoffrice.mytopgames.R
import onoffrice.mytopgames.data.models.Top
import onoffrice.mytopgames.data.models.TopGamesList
import onoffrice.mytopgames.ui.adapter.GameClickListener
import onoffrice.mytopgames.ui.adapter.GamesAdapter
import onoffrice.mytopgames.ui.base.BaseActivity
import onoffrice.mytopgames.ui.gamedetail.GameDetailActivity
import onoffrice.mytopgames.ui.gamedetail.createGameDetailIntent
import onoffrice.mytopgames.utils.extension.startActivitySlideTransition
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast

const val DEFAULT_OFFSET_VALUE      = 0
const val DEFAULT_OFFSET_PAGINATION = 10

class TopGamesActivity : BaseActivity(), TopGamesContract.View{

    private var offset = DEFAULT_OFFSET_VALUE
    private var isLoading: Boolean = false

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
        setContentView(R.layout.activity_top_games)
        setToolbar(getString(R.string.top_game_toolbar_title),true)
        setListeners()
        presenter.getTopGames(offset, isNetworkAvailable())
    }

    private fun setListeners() {
        //Pull to refresh listener
        swipeRefresh.setOnRefreshListener {
            offset = DEFAULT_OFFSET_VALUE
            presenter.getTopGames(offset, isNetworkAvailable())
        }

        setInfiniteScroll()
    }

    /** Make's new requests when user scrolls the list to the last item */
    private fun setInfiniteScroll() {
        topGamesRv?.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                //direction = 1 = list ends
                if (!recyclerView.canScrollVertically(1 ) && !isLoading){
                    isLoading = true
                    offset += DEFAULT_OFFSET_PAGINATION
                    presenter.getTopGames(offset, isNetworkAvailable())
                    isLoading = false
                }
            }
        })
    }

    /** Updates the top games list **/
    override fun setTopGames(topGamesResponse: TopGamesList, isOffline: Boolean?) {
        topGamesResponse.top?.let {
            //If list is empy or user is offline or offset is the initial, overrides the list
            //usually happens the user pull to refresh
            if (gamesAdapter.list.isEmpty() || isOffline == true || offset == DEFAULT_OFFSET_VALUE) {
                gamesAdapter.list = it.toMutableList()
            }
            else {
                //Only adds new items o list, usually whe user scrolls down
                gamesAdapter.list.addAll(it)
                gamesAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun displayLoading(loading: Boolean) {
        swipeRefresh.isRefreshing = loading
    }

    override fun displayError(message: String?) {
        toast(message ?: getString(R.string.common_error) )
    }
}

fun Context.createTopGamesIntent() = intentFor<TopGamesActivity>()

