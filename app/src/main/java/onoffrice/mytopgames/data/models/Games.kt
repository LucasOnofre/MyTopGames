package onoffrice.mytopgames.data.models

data class Game(
    val _id: Int?               = 0,
    val box: Box?               = Box(),
    val name: String?           = "",
    val logo: Logo?             = Logo(),
    val locale: String?         = "",
    val popularity: Int?        = 0,
    val giantbomb_id: Int?      = 0,
    val localized_name: String? = ""
)

data class Box(
    val large: String?    = "",
    val small: String?    = "",
    val medium: String?   = "",
    val template: String? = ""
)

data class Logo(
    val small: String?    = "",
    val large: String?    = "",
    val medium: String?   = "",
    val template: String? = ""
)
