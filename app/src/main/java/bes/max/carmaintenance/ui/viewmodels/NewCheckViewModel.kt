package bes.max.carmaintenance.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.data.PlannedCheckDao
import bes.max.carmaintenance.domain.models.PlannedCheck
import kotlinx.coroutines.launch

class NewCheckViewModel(private val plannedCheckDao: PlannedCheckDao): ViewModel() {

    fun insertPlannedCheck(work: String, date: String) {
        viewModelScope.launch {
            plannedCheckDao.insert(PlannedCheck(0, work, date))
        }
    }

}