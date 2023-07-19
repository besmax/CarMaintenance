package bes.max.carmaintenance.domain

import bes.max.carmaintenance.domain.models.PlannedCheck
import kotlinx.coroutines.flow.Flow

interface PlannedCheckRepository {

    suspend fun insertPlannedCheckInDb(check: PlannedCheck)

    suspend fun deletePlannedCheckFromDb(check: PlannedCheck)

    fun getAllPlannedCheckFromDb(): Flow<List<PlannedCheck>>

    fun getPlannedCheckById(checkId: Long): Flow<PlannedCheck>
}