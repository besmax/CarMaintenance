package bes.max.carmaintenance.data.repositories

import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.domain.PlannedCheckRepository
import bes.max.carmaintenance.domain.models.PlannedCheck
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
class PlannedCheckRepositoryImpl(
    private val plannedCheckDao: PlannedCheckDao
) : PlannedCheckRepository {
    override suspend fun insertPlannedCheckInDb(check: PlannedCheck) {
        plannedCheckDao.insert(check)
    }

    override suspend fun deletePlannedCheckFromDb(check: PlannedCheck) {
        plannedCheckDao.delete(check)
    }

    override fun getAllPlannedCheckFromDb(): Flow<List<PlannedCheck>> {
        return plannedCheckDao.getAll()
    }

    override fun getPlannedCheckById(checkId: Long): Flow<PlannedCheck> {
        return plannedCheckDao.get(checkId)
    }
}