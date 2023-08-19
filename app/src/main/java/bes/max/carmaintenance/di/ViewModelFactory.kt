package bes.max.carmaintenance.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel: ViewModel
        try {
            viewModel = viewModelProviders[modelClass]?.get() as T
        } catch (e: Exception) {
            throw IllegalArgumentException("ViewModelFactory: Unknown ViewModel")
        }
        return viewModel
    }

}