package bes.max.carmaintenance.network

import bes.max.carmaintenance.model.GoogleApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://sheets.googleapis.com"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface GoogleSpreadSheetsApiService {

    @GET("/v4/spreadsheets/1vU-aHtm0Fx0qHRJ3vX34U148_lHYgwO8SHRjZHZzKXc/values/main?alt=json&key=AIzaSyBM9iurH8tkiVp-wcEscH_-RlcdMIFKLoQ")
    suspend fun getData() : Response<GoogleApiResponse>
}

object GoogleSpreadSheetsApi {
    val googleSpreadSheetsApiService: GoogleSpreadSheetsApiService by lazy {
        retrofit.create(GoogleSpreadSheetsApiService::class.java)
    }
}

enum class GoogleSpreadSheetsApiStatus {
    DONE, ERROR, NO_DATA_FOUND;

    override fun toString(): String {
        return when (this) {
            DONE -> "All is ok"
            ERROR -> "Response to server was not succesful"
            NO_DATA_FOUND -> "Response to server was succesful, but data is empty"
        }
    }
}