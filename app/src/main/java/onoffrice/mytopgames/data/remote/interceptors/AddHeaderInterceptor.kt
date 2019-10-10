package onoffrice.mytopgames.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import onoffrice.mytopgames.data.models.RequestHeaderInterceptor
import java.io.IOException

class AddHeaderInterceptor : Interceptor {
    private val REQUEST_HEADER_AUTHENTICATION =
        listOf(
            RequestHeaderInterceptor("Client-ID", "xmhij3gr6k55dw07e87yvxl1v8pkda"),
            RequestHeaderInterceptor("Accept", "application/vnd.twitchtv.v5+json"))

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        var request  = original

        //Adds every item on request header list on the request header
            request = original.newBuilder().also { request ->
                REQUEST_HEADER_AUTHENTICATION.forEach {
                    request.addHeader(it.name, it.value)
                }
            }
                .method(original.method(), original.body())
                .build()

        return chain.proceed(request)
    }
}