package bes.max.carmaintenance.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import bes.max.carmaintenance.data.CheckDao

class ChecksViewModelFactory(private val dao: CheckDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChecksViewModel::class.java)) {
            return ChecksViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}