package bes.max.carmaintenance.data.repositories

import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.mappers.GoogleApiResponseMapper
import bes.max.carmaintenance.domain.models.Check
import bes.max.carmaintenance.domain.CheckRepository
import kotlinx.coroutines.flow.Flow

class ChecksRepositoryImpl(
    private val networkClient: NetworkClient,
    private val checkDao: CheckDao,
    private val mapper: GoogleApiResponseMapper
) : CheckRepository {

    override suspend fun getDataFromRemote(): List<Check> {
        val response = networkClient.getData()
        return if (response.values.isEmpty()) {
            emptyList()
        } else {
            mapper.toListOfCheck(response)
        }
    }

    override suspend fun insertAllInDb(checks: List<Check>) {
        checkDao.insertAll(checks)
    }

    override fun getAllFromDb(): Flow<List<Check>> {
        return checkDao.getAll()
    }
}