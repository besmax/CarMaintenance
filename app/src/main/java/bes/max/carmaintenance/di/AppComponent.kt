package bes.max.carmaintenance.di

import bes.max.carmaintenance.data.NetworkClient
import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.domain.CheckRepository
import dagger.Component

@Component(modules = [NetworkModule::class, DatabaseModule::class, RepositoryModule::class])
interface AppComponent {

    fun getRetrofitNetworkClient(): NetworkClient

    fun getCheckDao(): CheckDao

    fun getPlannedCheckDao(): PlannedCheckDao

    fun getCheckRepository(): CheckRepository
}