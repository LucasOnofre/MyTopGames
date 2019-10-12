package onoffrice.mytopgames.ui.topgames

import io.reactivex.disposables.CompositeDisposable
import onoffrice.mytopgames.data.local.PreferencesHelper
import onoffrice.mytopgames.data.remote.datasource.GamesDataSource
import onoffrice.mytopgames.utils.extension.singleSubscribe

class TopGamesPresenter : TopGamesContract.Presenter {

    private var view: TopGamesContract.View? = null
    private var gamesDataSource = GamesDataSource

    private lateinit var disposable: CompositeDisposable

    override fun attachView(mvpView: TopGamesContract.View?) {
        view       = mvpView
        disposable = CompositeDisposable()
    }

    override fun getTopGames(page: Int, hasNetworkConnection: Boolean) {

        if (hasNetworkConnection) {
            view?.displayLoading(true)
            disposable.add(gamesDataSource.getTopGames(page).singleSubscribe(
                onSuccess = {
                    //Saves response to offline use
                    PreferencesHelper.games = it

                    view?.displayLoading(false)
                    view?.setTopGames(it)
                },
                onError = {
                    view?.displayLoading(false)
                    view?.displayError(it.message)
                }
            ))
        } else {
            showPreviousResult()
            handleNetworkError()
        }
    }

    private fun handleNetworkError() {
        view?.displayLoading(false)
        if (PreferencesHelper.isOnline) {
            view?.displayError("Sem internet, favor verificar WIFI ou pacote de dados")
            PreferencesHelper.isOnline = false
        }
    }

    private fun showPreviousResult() {
        PreferencesHelper.games?.let {
            if (it.top?.isNullOrEmpty() == false) {
                view?.setTopGames(it, true)
            }
        }
    }

    override fun detachView() {
        view = null
        disposable.dispose()
    }
}