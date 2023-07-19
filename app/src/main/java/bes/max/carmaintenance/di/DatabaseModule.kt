package bes.max.carmaintenance.di
import android.content.Context
import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.data.db.CheckDatabase
import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.data.mappers.GoogleApiResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule() {

    @Provides
    fun provideCheckDatabase(@ApplicationContext context: Context) : CheckDatabase {
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