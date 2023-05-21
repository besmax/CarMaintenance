package bes.max.carmaintenance.data

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

    @Query("SELECT * FROM checks_table ORDER BY check_id DESC")
    fun getAll() : LiveData<List<Check>>

    @Query("SELECT * FROM checks_table WHERE check_id = :checkId")
    fun get(checkId: Long) : LiveData<Check>
}