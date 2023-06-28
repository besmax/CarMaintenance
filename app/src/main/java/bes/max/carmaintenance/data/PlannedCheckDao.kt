package bes.max.carmaintenance.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import bes.max.carmaintenance.model.PlannedCheck
import kotlinx.coroutines.flow.Flow

@Dao
interface PlannedCheckDao {
    @Insert
    suspend fun insert(check: PlannedCheck)

    @Insert
    suspend fun insertAll(checks: List<PlannedCheck>)

    @Update
    suspend fun update(check: PlannedCheck)

    @Delete
    suspend fun delete(check: PlannedCheck)

    @Query("SELECT * FROM planned_checks_table ORDER BY planned_check_id DESC")
    fun getAll(): Flow<List<PlannedCheck>>

    @Query("SELECT * FROM planned_checks_table WHERE planned_check_id = :checkId")
    fun get(checkId: Long): LiveData<PlannedCheck>

}