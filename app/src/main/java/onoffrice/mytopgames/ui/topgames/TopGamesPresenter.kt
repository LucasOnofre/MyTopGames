package onoffrice.mytopgames.ui.topgames

import io.reactivex.disposables.CompositeDisposable
import onoffrice.mytopgames.data.remote.TopGamesContract
import onoffrice.mytopgames.data.remote.datasource.GamesDataSource
import onoffrice.mytopgames.utils.extension.singleSubscribe

class TopGamesPresenter : TopGamesContract.Presenter {


    private var view: TopGamesContract.View?    = null
    private var gamesDataSource = GamesDataSource

    private lateinit var disposable: CompositeDisposable

    override fun attachView(mvpView: TopGamesContract.View?) {
        view       = mvpView
        disposable = CompositeDisposable()
    }

    override fun getTopGames(page: Int) {
        view?.displayLoading(true)
        disposable.add(gamesDataSource.getTopGames(page).singleSubscribe(
            onSuccess = {
                view?.displayLoading(false)
                view?.setTopGames(it)
            },
            onError = { e, retrofitError ->
                view?.displayLoading(false)
                view?.displayError(e.message)
            }
        ))
    }

    override fun detachView() {
        view = null
        disposable.dispose()
    }
}