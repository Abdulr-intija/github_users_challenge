package com.intija.githubx.views.githubuser

import androidx.lifecycle.ViewModel
import com.intija.githubx.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GithubUserViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GithubUserViewModel::class)
    abstract fun bindGithubUserViewModel(githubUserViewModel: GithubUserViewModel):  ViewModel
}