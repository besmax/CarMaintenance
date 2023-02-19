package bes.max.carmaintenance

import androidx.lifecycle.LiveData
import androidx.room.*
import bes.max.carmaintenance.model.Check

@Dao
interface CheckDao {

    @Insert
    suspend fun insert(check: Check)

    @Insert
    suspend fun insertAll(checks: List<Check>)

    @Update
    suspend fun update(check: Check)

    @Delete
    suspend fun delete(check: Check)

    @Query("SELECT * FROM check_table ORDER BY checkId DESC")
    fun getAll() : LiveData<List<Check>>

    @Query("SELECT * FROM check_table WHERE checkId = :checkId")
    fun get(checkId: Long) : LiveData<Check>
}