package bes.max.carmaintenance.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.data.PlannedCheckDao
import bes.max.carmaintenance.domain.models.PlannedCheck
import kotlinx.coroutines.launch

class PlannedChecksViewModel(private val plannedCheckDao: PlannedCheckDao) : ViewModel() {

    var plannedChecks: LiveData<List<PlannedCheck>> = plannedCheckDao.getAll().asLiveData()

    fun deletePlannedCheck(plannedCheck: PlannedCheck) {
        viewModelScope.launch {
            plannedCheckDao.delete(plannedCheck)
        }
    }
}