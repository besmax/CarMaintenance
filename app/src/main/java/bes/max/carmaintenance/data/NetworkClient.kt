package bes.max.carmaintenance.data

import bes.max.carmaintenance.data.dto.GoogleApiResponse

interface NetworkClient {

    suspend fun getData(): GoogleApiResponse

}