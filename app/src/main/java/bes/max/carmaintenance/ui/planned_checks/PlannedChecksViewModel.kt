package bes.max.carmaintenance.ui.planned_checks

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.domain.models.PlannedCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlannedChecksViewModel @Inject constructor(private val plannedCheckDao: PlannedCheckDao) : ViewModel() {

    var plannedChecks: LiveData<List<PlannedCheck>> = plannedCheckDao.getAll().asLiveData()

    fun deletePlannedCheck(plannedCheck: PlannedCheck) {
        viewModelScope.launch {
            plannedCheckDao.delete(plannedCheck)
        }
    }
}