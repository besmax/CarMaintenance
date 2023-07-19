package bes.max.carmaintenance.ui.planned_checks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bes.max.carmaintenance.data.db.PlannedCheckDao

class PlannedChecksViewModelFactory(private val plannedCheckDao: PlannedCheckDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlannedChecksViewModel::class.java)) {
            return PlannedChecksViewModel(plannedCheckDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}