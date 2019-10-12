package onoffrice.mytopgames.ui.topgames

import onoffrice.mytopgames.data.models.TopGamesList
import onoffrice.mytopgames.ui.base.BasePresenter

interface TopGamesContract {
    interface View {
        fun displayLoading(loading: Boolean)
        fun displayError(message: String?)
        fun setTopGames(topGamesResponse: TopGamesList, isOffline: Boolean? = false)
    }

    interface Presenter : BasePresenter<View> {
       fun getTopGames(page: Int, networkAvailable: Boolean)
    }
}