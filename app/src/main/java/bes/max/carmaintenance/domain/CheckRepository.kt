package bes.max.carmaintenance.domain

import bes.max.carmaintenance.domain.models.Check
import kotlinx.coroutines.flow.Flow

interface CheckRepository {

    suspend fun getDataFromRemote() : List<Check>

    suspend fun insertAllInDb(checks: List<Check>)

    fun getAllFromDb() : Flow<List<Check>>
}