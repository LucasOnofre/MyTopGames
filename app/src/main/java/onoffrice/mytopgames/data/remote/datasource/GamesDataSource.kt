package onoffrice.mytopgames.data.remote.datasource

import onoffrice.mytopgames.data.request.RetrofitSingle
import onoffrice.mytopgames.data.remote.interceptors.AddHeaderInterceptor
import onoffrice.mytopgames.data.request.services.GamesService
import onoffrice.mytopgames.utils.NetworkConstants


object GamesDataSource {

    private val service = RetrofitSingle.createService(
        url          = NetworkConstants.GAMES,
        serviceClass = GamesService::class.java,
        interceptors = listOf(AddHeaderInterceptor())
    )

    fun getTopGames(page: Int) = service.getTopGames(page)

}