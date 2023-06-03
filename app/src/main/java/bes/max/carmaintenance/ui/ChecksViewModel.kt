package bes.max.carmaintenance.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bes.max.carmaintenance.data.CheckDao
import bes.max.carmaintenance.model.Check
import bes.max.carmaintenance.model.GoogleApiResponse
import bes.max.carmaintenance.network.GoogleSpreadSheetsApi.googleSpreadSheetsApiService
import bes.max.carmaintenance.network.GoogleSpreadSheetsApiStatus
import kotlinx.coroutines.launch

class ChecksViewModel(private val dao: CheckDao) : ViewModel() {

    val checks = MutableLiveData<List<Check>>()
    val status = MutableLiveData<GoogleSpreadSheetsApiStatus>()


    init {
        getDataFromGoogleSheets()
    }

    fun getDataFromGoogleSheets() {
        viewModelScope.launch {
            try {
                val result = googleSpreadSheetsApiService.getData()
                if (result.isSuccessful) {
                    if (result.body() != null) {
                        val responseBody: GoogleApiResponse? = result.body()
                        checks.value = responseBody?.convertDataToCheckFormat()
                        status.value = GoogleSpreadSheetsApiStatus.DONE
                    } else {
                        status.value = GoogleSpreadSheetsApiStatus.NO_DATA_FOUND
                    }
                } else {
                    status.value = GoogleSpreadSheetsApiStatus.ERROR
                }
            } catch (e: Exception) {
                status.value = GoogleSpreadSheetsApiStatus.ERROR
                e.printStackTrace()
            }
        }
    }

    fun sortByDate() {
        val sortedList = checks.value?.sortedBy { it.date }
        checks.value = sortedList ?: checks.value
    }

}