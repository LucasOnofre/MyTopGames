package onoffrice.mytopgames.data.remote

import onoffrice.mytopgames.data.models.TopGamesList
import onoffrice.mytopgames.ui.base.BasePresenter
import onoffrice.mytopgames.ui.base.BaseView

interface TopGamesContract {
    interface View : BaseView<Presenter> {
        fun displayLoading(loading: Boolean)
        fun displayError(message: String?)
        fun setTopGames(topGamesResponse: TopGamesList)
    }

    interface Presenter : BasePresenter<View> {
       fun getTopGames(page: Int)
    }
}