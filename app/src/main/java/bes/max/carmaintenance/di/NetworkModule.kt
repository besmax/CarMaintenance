package bes.max.carmaintenance.di

import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.network.RetrofitNetworkClient
import dagger.Module
import dagger.Provides

@Module
class NetworkModule {

    @Provides
    fun provideRetrofitNetworkClient() : NetworkClient {
        return RetrofitNetworkClient()
    }
}