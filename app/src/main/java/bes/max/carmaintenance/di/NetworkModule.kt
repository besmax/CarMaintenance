package bes.max.carmaintenance.di

import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideRetrofitNetworkClient() : NetworkClient {
        return RetrofitNetworkClient()
    }
}