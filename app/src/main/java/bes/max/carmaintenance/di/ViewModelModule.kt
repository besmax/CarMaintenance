package bes.max.carmaintenance.di

import androidx.lifecycle.ViewModel
import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.domain.CheckRepository
import bes.max.carmaintenance.ui.checks.ChecksViewModel
import bes.max.carmaintenance.ui.new_check.NewCheckViewModel
import bes.max.carmaintenance.ui.planned_checks.PlannedChecksViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @IntoMap
    @ViewModelKey(ChecksViewModel::class)
    @Provides
    fun provideChecksViewModel(checkRepository: CheckRepository) : ViewModel {
        return ChecksViewModel(checkRepository)
    }

    @IntoMap
    @ViewModelKey(NewCheckViewModel::class)
    @Provides
    fun provideNewCheckViewModel(plannedCheckDao: PlannedCheckDao) : ViewModel {
        return NewCheckViewModel(plannedCheckDao)
    }

    @IntoMap
    @ViewModelKey(PlannedChecksViewModel::class)
    @Provides
    fun providePlannedChecksViewModel(plannedCheckDao: PlannedCheckDao) : ViewModel {
        return PlannedChecksViewModel(plannedCheckDao)
    }

}