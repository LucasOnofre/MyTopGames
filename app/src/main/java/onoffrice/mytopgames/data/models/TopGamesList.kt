package onoffrice.mytopgames.data.models

import java.io.Serializable

data class TopGamesList(
    val _total: Int?    = 0,
    val top: List<Top>? = listOf()
): Serializable

data class Top(
    val game: Game?    = Game(),
    val viewers: Int?  = 0,
    val channels: Int? = 0
): Serializable
