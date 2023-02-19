package bes.max.carmaintenance

import androidx.lifecycle.ViewModel

class ChecksViewModel(val dao: CheckDao) : ViewModel() {

    val checks =dao.getAll()

}