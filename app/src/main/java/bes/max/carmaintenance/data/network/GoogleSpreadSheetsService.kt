package bes.max.carmaintenance.data.network

import bes.max.carmaintenance.data.dto.GoogleApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface GoogleSpreadSheetsService {

    @GET("/v4/spreadsheets/1vU-aHtm0Fx0qHRJ3vX34U148_lHYgwO8SHRjZHZzKXc/values/main?alt=json&key=AIzaSyBM9iurH8tkiVp-wcEscH_-RlcdMIFKLoQ")
    suspend fun getData(): Response<GoogleApiResponse>
}
