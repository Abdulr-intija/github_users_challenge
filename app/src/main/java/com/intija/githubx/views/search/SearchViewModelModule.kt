package com.intija.githubx.views.search

import androidx.lifecycle.ViewModel
import com.intija.githubx.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(searchViewModel: SearchViewModel):  ViewModel
}