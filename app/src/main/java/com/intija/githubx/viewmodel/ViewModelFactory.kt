package com.intija.githubx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider

@Module
class ViewModelFactory {
    @Provides
    @Suppress("UNCHECKED_CAST")
    fun provideViewModelFactory(providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
            }
        }

}