package bes.max.carmaintenance.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bes.max.carmaintenance.domain.models.Check
import bes.max.carmaintenance.domain.models.PlannedCheck

@Database(entities = [Check::class, PlannedCheck::class], version = 3, exportSchema = false)
abstract class CheckDatabase : RoomDatabase() {

    abstract val checkDao: CheckDao
    abstract val plannedCheckDao: PlannedCheckDao

    companion object {
        @Volatile
        private var INSTANCE: CheckDatabase? = null

        fun getInstance(context: Context): CheckDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CheckDatabase::class.java,
                        "checks_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}