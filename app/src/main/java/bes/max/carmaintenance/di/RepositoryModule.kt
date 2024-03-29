package bes.max.carmaintenance.di

import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.data.mappers.GoogleApiResponseMapper
import bes.max.carmaintenance.data.repositories.ChecksRepositoryImpl
import bes.max.carmaintenance.domain.CheckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideCheckRepository(
        networkClient: NetworkClient,
        checkDao: CheckDao,
        mapper: GoogleApiResponseMapper
    ): CheckRepository {
        return ChecksRepositoryImpl(
            networkClient = networkClient,
            checkDao = checkDao,
            mapper = mapper
        )
    }

    @Provides
    fun provideGoogleApiResponseMapper(): GoogleApiResponseMapper {
        return GoogleApiResponseMapper()
    }

}