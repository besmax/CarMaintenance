package bes.max.carmaintenance.data.network

import android.util.Log
import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.dto.GoogleApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

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
        var result = GoogleApiResponse("", "", emptyList())
        try {
            val response = googleSpreadSheetsApiService.getData()
            if (response.isSuccessful && response.body() != null) {
                result = response.body()!!
            }
        } catch (e: Exception) {
            Log.e(
                "RetrofitNetworkClient",
                "Error during catching data from remote: ${e.toString()}"
            )
        }
        return result

    }
}