package bes.max.carmaintenance.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bes.max.carmaintenance.model.Check

@Database(entities = [Check::class], version = 2, exportSchema = false)
abstract class CheckDatabase : RoomDatabase() {

    abstract val checkDao: CheckDao

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
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}