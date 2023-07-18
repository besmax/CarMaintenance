package bes.max.carmaintenance.di

import android.content.Context
import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.data.db.CheckDatabase
import bes.max.carmaintenance.data.db.PlannedCheckDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    fun provideCheckDatabase() : CheckDatabase {
        return CheckDatabase.getInstance(context)
    }

    @Provides
    fun provideCheckDao(checkDatabase: CheckDatabase) : CheckDao {
        return checkDatabase.checkDao
    }

    @Provides
    fun providePlannedCheckDao(checkDatabase: CheckDatabase) : PlannedCheckDao {
        return checkDatabase.plannedCheckDao
    }

}