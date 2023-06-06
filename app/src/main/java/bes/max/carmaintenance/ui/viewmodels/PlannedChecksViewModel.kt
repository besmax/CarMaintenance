package bes.max.carmaintenance.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import bes.max.carmaintenance.data.PlannedCheckDao
import bes.max.carmaintenance.model.PlannedCheck

class PlannedChecksViewModel(private val plannedCheckDao: PlannedCheckDao) : ViewModel() {

    var plannedChecks: LiveData<List<PlannedCheck>> = plannedCheckDao.getAll().asLiveData()


}