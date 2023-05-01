package bes.max.carmaintenance.ui

import androidx.lifecycle.ViewModel
import bes.max.carmaintenance.data.CheckDao

class ChecksViewModel(val dao: CheckDao) : ViewModel() {

    val checks = dao.getAll()

}