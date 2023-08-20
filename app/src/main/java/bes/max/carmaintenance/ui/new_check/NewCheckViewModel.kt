package bes.max.carmaintenance.ui.new_check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.data.db.PlannedCheckDao
import bes.max.carmaintenance.domain.models.PlannedCheck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewCheckViewModel @Inject constructor(private val plannedCheckDao: PlannedCheckDao): ViewModel() {

    fun insertPlannedCheck(work: String, date: String) {
        viewModelScope.launch {
            plannedCheckDao.insert(PlannedCheck(0, work, date))
        }
    }

}