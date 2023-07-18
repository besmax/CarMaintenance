package bes.max.carmaintenance.ui.checks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.domain.CheckRepository
import bes.max.carmaintenance.domain.models.Check
import kotlinx.coroutines.launch

class ChecksViewModel(private val checkRepository: CheckRepository) : ViewModel() {

    val checks = MutableLiveData<List<Check>>()
    //val status = MutableLiveData<GoogleSpreadSheetsApiStatus>()
    var check: Check? = null
    var date = MutableLiveData<String>("")


    init {
        getDataFromGoogleSheets()
    }

    fun getDataFromGoogleSheets() {
        viewModelScope.launch {
            checks.value = checkRepository.getDataFromRemote()
        }
    }

    fun sortByDate() {
        val sortedList = checks.value?.sortedBy { it.date }
        checks.value = sortedList ?: checks.value
    }

}