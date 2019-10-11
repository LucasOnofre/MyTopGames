package onoffrice.mytopgames.data.request.services

import io.reactivex.Single
import onoffrice.mytopgames.data.models.TopGamesList
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesService {

    @GET("top")
    fun getTopGames(@Query("offset") page: Int): Single<TopGamesList>


}