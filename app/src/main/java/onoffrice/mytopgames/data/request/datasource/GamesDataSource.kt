package onoffrice.mytopgames.data.request.datasource

import onoffrice.mytopgames.data.request.RetrofitSingle
import onoffrice.mytopgames.data.request.services.GamesService
import java.io.File


object GamesDataSource {

    val service = RetrofitSingle.createService(
        serviceClass = GamesService::class.java,
        interceptors = listOf(AddCookieInterceptor(), ReceivedCookieInterceptor()),
        url = NetworkConstants.ACCOUNT_URL
    )

    fun updatePicture(file: File): Single<SignUpResponse> {
        val filePart: MultipartBody.Part =
            MultipartBody.Part.createFormData("picture", file.name, RequestBody.create(MediaType.parse("image/*"), file))

        return service.updatePicture(filePart)
    }

    fun updateAbout(request: UpdateAboutRequest) = service.updateAbout(request)

    fun updatePassword(request: UpdatePasswordRequest) = service.updatePassword(request)

    fun getAddresses(): Single<List<AddressModel>> = service.getAddresses().map { it.map { it.toAddressModel() } }

    fun createAddress(request: CreateAddressRequest) = service.createAddress(request)

    fun deleteAddress(addressId: String) = service.deleteAddress(addressId)
}