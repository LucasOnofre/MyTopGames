package onoffrice.mytopgames.data.models

data class TopGamesList(
    val _total: Int?    = 0,
    val top: List<Top>? = listOf()
)

data class Top(
    val game: Game?    = Game(),
    val viewers: Int?  = 0,
    val channels: Int? = 0
)
