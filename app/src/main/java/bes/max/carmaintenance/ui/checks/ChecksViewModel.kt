package bes.max.carmaintenance.ui.checks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.domain.CheckRepository
import bes.max.carmaintenance.domain.models.Check
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChecksViewModel @Inject constructor(private val checkRepository: CheckRepository) :
    ViewModel() {

    private val _checks = MutableLiveData<List<Check>>()
    val checks: LiveData<List<Check>> = _checks
    var check: Check? = null
    var date = MutableLiveData<String>("")


    init {
        getDataFromGoogleSheets()
    }

    fun getDataFromGoogleSheets() {
        viewModelScope.launch {
            _checks.value = checkRepository.getDataFromRemote()
        }
    }

    fun sortByDate() {
        val sortedList = checks.value?.sortedBy { it.date }
        _checks.value = sortedList ?: checks.value
    }

}