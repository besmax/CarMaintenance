package bes.max.carmaintenance.data.network

import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.dto.GoogleApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient : NetworkClient {

    private val BASE_URL = "https://sheets.googleapis.com"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val googleSpreadSheetsApiService: GoogleSpreadSheetsService by lazy {
        retrofit.create(GoogleSpreadSheetsService::class.java)
    }

    override suspend fun getData(): GoogleApiResponse {
        val result = googleSpreadSheetsApiService.getData()
        return if (result.isSuccessful && result.body() != null) {
            result.body()!!
        } else {
            GoogleApiResponse("", "", emptyList())
        }
    }
}