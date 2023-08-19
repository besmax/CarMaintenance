package bes.max.carmaintenance.di

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {

    fun getViewModelFactory() : ViewModelFactory

}