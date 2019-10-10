package onoffrice.mytopgames.utils

import onoffrice.mytopgames.BuildConfig

object NetworkConstants {

    //1xx Informational
    const val CODE_WITHOUT_NETWORK = 0

    //2xx Success
    const val CODE_RESPONSE_SUCCESS = 200

    //3xx Redirection
    const val CODE_NOT_FOUND = 340

    //4xx Client Error
    const val CODE_TIMEOUT = 408
    const val CODE_RESPONSE_UNAUTHORIZED = 401
    const val CODE_BAD_REQUEST = 400
    const val CODE_FORBIDDEN = 403

    //5xx Server Error
    const val CODE_UNKNOWN = 500


    //API HEADERS
    val CLIENT_ID_HEADER        : String = BuildConfig.CLIENT_ID_HEADER
    val API_VERSION_JSON_HEADER : String = BuildConfig.API_VERSION_JSON_HEADER

    //API URLs
    val BASE_URL : String = BuildConfig.BASE_URL

    val GAMES = "${BASE_URL}games/"

}