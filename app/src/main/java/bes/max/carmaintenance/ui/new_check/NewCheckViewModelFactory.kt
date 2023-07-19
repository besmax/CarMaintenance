package bes.max.carmaintenance.ui.new_check

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bes.max.carmaintenance.data.db.PlannedCheckDao

class NewCheckViewModelFactory(private val plannedCheckDao: PlannedCheckDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewCheckViewModel::class.java)) {
            return NewCheckViewModel(plannedCheckDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}