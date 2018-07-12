package online.erthru.tpk.network

import erthru.erthru.tempatpelayanankesehatan.network.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig{

    companion object {
        const val BASE_URL = "https://maps.googleapis.com/maps/api/place/"
        const val API_KEY = "YOUR_GOOGLE_PLACE_API_KEY"
    }

    private fun retrofit() : Retrofit{

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    fun instance() : ApiInterface {
        return retrofit().create(ApiInterface::class.java)
    }

}
