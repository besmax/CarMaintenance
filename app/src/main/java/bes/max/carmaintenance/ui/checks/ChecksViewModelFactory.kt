package bes.max.carmaintenance.ui.checks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bes.max.carmaintenance.data.db.CheckDao
import bes.max.carmaintenance.domain.CheckRepository

class ChecksViewModelFactory(private val checkRepository: CheckRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChecksViewModel::class.java)) {
            return ChecksViewModel(checkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}