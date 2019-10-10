package onoffrice.mytopgames.data.request.services

import io.reactivex.Single
import onoffrice.mytopgames.data.models.TopGamesList
import retrofit2.http.GET

interface GamesService {

    @GET("top")
    fun getTopGames(): Single<TopGamesList>


}